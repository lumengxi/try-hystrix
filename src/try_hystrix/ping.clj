(ns try-hystrix.ping
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
  {:hystrix/command-key             "ping-fn"
   :hystrix/group-key               "ping"
   :hystrix/threads                 5
   :hystrix/queue-size              5
   :hystrix/timeout-ms              1000
   :hystrix/breaker-request-volume  20
   :hystrix/breaker-error-percent   50
   :hystrix/breaker-sleep-window-ms 5000}
  [n]
  (let [urls  (mk-urls! n)
        resps (map fetch-post urls)]
    resps))
