(ns try-hystrix.core-test
  (:require [cheshire.core :as cheshire]
            [clojure.test :refer :all]
            [try-hystrix.handler :refer :all]
            [ring.mock.request :as mock]))

(defn parse-body [body]
  (cheshire/parse-string (slurp body) true))

(deftest a-test

  (testing "Test GET request to /hello?name={a-name} returns expected response"
    (let [response (app (-> (mock/request :get  "/api/ping")))
          body     (parse-body (:body response))]
      (is (= (:status response) 200))
      (is (= (:result body) 3)))))
