(ns try-hystrix.utils
  (:import (com.netflix.config ConfigurationManager)
           (com.netflix.hystrix HystrixCommandKey$Factory HystrixCommandMetrics HystrixCircuitBreaker$Factory)))

(defn set-hystrix-config!
  [cmd-key k v]
  (let [key (format "hystrix.command.%s.%s" (name cmd-key) k)]
    (.setProperty (ConfigurationManager/getConfigInstance) key v)))

(defn get-hystrix-key
  [cmd-key]
  (HystrixCommandKey$Factory/asKey (name cmd-key)))

(defn get-metrics
  [hystrix-cmd-key]
  (HystrixCommandMetrics/getInstance hystrix-cmd-key))

(defn get-circuit-breaker
  [hystrix-cmd-key]
  (HystrixCircuitBreaker$Factory/getInstance hystrix-cmd-key))
