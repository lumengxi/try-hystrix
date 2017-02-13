(ns try-hystrix.api.hello-world
  (:require [clojure.tools.logging :as log]
            [com.netflix.hystrix.core :as hystrix]))

(defn hello-world
  [name max-sleep-ms]
  (fn []
    (let [sleep-ms (rand-int max-sleep-ms)]
      (Thread/sleep sleep-ms)
      (format "Hello %s after sleeping for %d ms" name sleep-ms))))

(defn run-hello-world
  [name]
  (-> {:run-fn (hello-world name 1000)
       :command-key "hello-world"
       :group-key "api"}
      hystrix/command
      hystrix/execute))

(defn async-run-hello-world
  [name]
  (let [f (-> {:run-fn (hello-world name 5000)
               :command-key "hello-world-async"
               :group-key "api"}
              hystrix/command
              hystrix/queue)]
    @f))
