(ns mb.server.main
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [mb.core :as c]
            [cljs.nodejs :as nodejs]
            [cljs.core.async :refer [put! chan <!]]))

(def -test-configs #js [#js {"global" #js { "port" 3000 }
                             "global+test" #js { "port" 3001  }}])

(def -test-scopes #js ["global", "test"])

(defn default-middlewear [req res next]
  (do
    (.send res "Hello, world")
    (next)))

(defn start-server [port]
  (let [express (js/require "express")
        app (express)]
    (.use app default-middlewear)
    (.listen app port (fn []
                   (println "Listening on port" port)
                   ))))

(defn -main [& args]


  (let [config (thicket.configuration/resolve -test-scopes -test-configs)
        port (.-port config)]

    (do
      (println "Starting server on port" port)
      (start-server port))))

(nodejs/enable-util-print!)
(set! *main-cli-fn* -main)
