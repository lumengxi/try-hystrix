(ns try-hystrix.handler
  (:require [compojure.api.sweet :refer :all]
            [schema.core :as s]
            [ring.util.http-response :refer :all]
            [ring.middleware.json :as ring-json]
            [ring.middleware.logger :as ring-logger]
            [try-hystrix.api.ping :as ping]))

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

      (GET "/ping" []
        :body-params [n :- s/Num]
        :summary "adds two numbers together"
        (ping/fetch-posts n)))))

(def app
  (-> app-routes
      ring-json/wrap-json-response
      ring-logger/wrap-with-logger))
