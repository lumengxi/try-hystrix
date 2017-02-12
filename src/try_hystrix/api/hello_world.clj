(ns try-hystrix.api.hello-world
  (:require [clojure.tools.logging :as log]
            [com.netflix.hystrix.core :as hystrix]))

(defn hello-world
  [name]
  (str "Hello" name "!"))


(defn run-hello-world
  [name]
  (-> {:run-fn (hello-world name)
       :fallback-fn (constantly nil)
       :command-key "hello-world"
       :group-key "api"}
      hystrix/command
      hystrix/execute))

(defn async-run-hello-world
  [name]
  (let [f (-> {:run-fn (hello-world name)
               :fallback-fn (constantly nil)
               :command-key "hello-world-async"
               :group-key "api"}
              hystrix/command
              hystrix/queue)]
    (log/info "Start to ")
    @f))
