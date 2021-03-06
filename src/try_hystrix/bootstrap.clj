(ns try-hystrix.bootstrap
  (:require [clojure.tools.logging :as log]
            [ring-jetty-hystrix-adapter.core :as jetty]
            [try-hystrix.handler :refer [app]]))

(defn -main [& args]
  (onelog.core/set-debug!)
  (jetty/run-jetty-with-hystrix
    app
    {:port 3001
     :max-threads 10
     :hystrix-servlet-path "/hystrix.stream"
     :connector-stats? true
     :handler-stats? true
     :join? false})
  (log/infof "Server running on port 3001..."))
