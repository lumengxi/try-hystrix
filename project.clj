(defproject try-hystrix "0.1.0-SNAPSHOT"
  :description "A Ring app to test Hystrix integrations"

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clj-time "0.11.0"] ; required due to bug in `lein-ring uberwar`
                 [cheshire "5.6.3"]
                 [metosin/compojure-api "1.1.1"]
                 [clj-http "2.2.0"]
                 [org.clojure/tools.logging "0.3.1"]
                 [log4j/log4j "1.2.17"]
                 [org.slf4j/slf4j-log4j12 "1.7.21"]
                 [com.netflix.hystrix/hystrix-clj "1.5.5"]
                 [ring-jetty-hystrix-adapter "0.2.0-RC1"]
                 [ring.middleware.logger "0.5.0"]
                 [ring/ring-json "0.4.0"]]

  :ring {:handler try-hystrix.handler/app}

  :javac-options ["-target" "1.8" "-source" "1.8" "-Xlint:-options"]

  :uberjar-name "try-hystrix.jar"

  :uberjar {:aot :all}

  :main try-hystrix.bootstrap

  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [ring/ring-mock "0.3.0"]]
                   :plugins [[lein-ring "0.9.7"]]

                   :cljfmt {:indents
                            {require [[:block 0]]
                             ns [[:block 0]]
                             #"^(?!:require|:import).*" [[:inner 0]]}}}})
