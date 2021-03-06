(ns mb.server.main
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [mb.core :as c]
            [cljs.nodejs :as nodejs]
            [cljs.core.async :refer [put! chan <!]]))

(def ^:private t (js/require "thicket"))

(def ^:private log (thicket.logging/logger "mb.server.main"))

(defn- default-middlewear [req res next]
  (do
    (.send res "Hello, world")
    (next)))

(defn- start-server [config]
  (let [express (js/require "express")
        app (express)
        port (.getOrError config "port")
        static-dirs (js->clj (.getOrError config "static-directories"))]

    (doseq [dir static-dirs]
      (.use app (.static express dir)))

    (.use app default-middlewear)

    (.listen app port (fn []
                   (.info log "Listening on port" port)))))

(defn- up [config]
  (.debug log "Going up")
  (let [port (.getOrError config "port")]
    (.debug log "Starting server on port" port)
    (start-server config)))

(defn- down [config]
  (.debug log "Going down"))

(defn- -main [& args]
  (aset js/goog "global" "setTimeout" js/setTimeout)
  (thicket.logging/root-set-log-level! "Debug")
  (thicket.logging/root-add-appender! (thicket.logging/console-log-appender))

  (go (let [container (<! (thicket.appkit/bootstrap up down))]
        (.start container))))

(nodejs/enable-util-print!)
(set! *main-cli-fn* -main)
