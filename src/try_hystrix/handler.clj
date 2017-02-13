(ns try-hystrix.handler
  (:require [compojure.api.sweet :refer :all]
            [schema.core :as s]
            [ring.util.http-response :refer :all]
            [ring.middleware.json :refer [wrap-json-response]]
            [ring.middleware.logger :refer [wrap-with-logger]]
            [try-hystrix.api.ping :as ping]
            [try-hystrix.api.hello-world :as hello-world]))

(def app-routes
  (api
    {:swagger
     {:ui "/"
      :spec "/swagger.json"
      :data {:info {:title "Try Hystrix"
                    :description "Hystrix playground"}
             :tags [{:name "api", :description "some apis"}]}}}

    (context "/api" []
      :tags ["api"]

      (POST "/ping" []
        :body-params [n :- s/Num]
        :summary "adds two numbers together"
        (ok (ping/fetch-posts n)))

      (POST "/hello-world" []
        :body-params [name :- s/Str]
        (ok (hello-world/run-hello-world name)))

      (POST "/hello-world-async" []
        :body-params [name :- s/Str]
        (ok (hello-world/async-run-hello-world name))))))

(def app
  (-> app-routes
      wrap-json-response
      wrap-with-logger))
