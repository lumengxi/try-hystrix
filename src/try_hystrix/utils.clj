(ns try-hystrix.utils
  (:import (com.netflix.config ConfigurationManager)
           (com.netflix.hystrix HystrixCommandKey$Factory HystrixCommandMetrics HystrixCircuitBreaker$Factory)))

(defn set-hystrix-config!
  [cmd-key k v]
  (let [key (format "hystrix.command.%s.%s" cmd-key k)]
    (.setProperty (ConfigurationManager/getConfigInstance) key v)))

(defn get-hystrix-key
  [cmd-key]
  (HystrixCommandKey$Factory/asKey cmd-key))

(defn get-metrics
  [cmd-key]
  (HystrixCommandMetrics/getInstance cmd-key))

(defn get-circuit-breaker
  [cmd-key]
  (HystrixCircuitBreaker$Factory/getInstance cmd-key))
