(ns try-hystrix.api.ping
  (:require [cheshire.core :refer [parse-string]]
            [clj-http.client :as http]
            [com.netflix.hystrix.core :as hystrix]))

(defn mk-urls!
  [n]
  (let [rand-post-ids (take n (repeatedly #(rand-int 10)))]
    (map #(format "http://jsonplaceholder.typicode.com/posts/%d" %) rand-post-ids)))

(defn- fetch-post
  [url]
  (-> url
      http/get
      :body
      (parse-string true)
      :title))

(hystrix/defcommand fetch-posts
  {:hystrix/command-key             "ping"
   :hystrix/group-key               "api"}
  [n]
  (let [urls  (mk-urls! n)
        resps (map fetch-post urls)]
    resps))
