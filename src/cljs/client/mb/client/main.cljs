(ns mb.client.main
  (:require-macros [cljs.core.async.macros :refer [go-loop]])
  (:require [mb.core :as c]
            [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [cljs.core.async :refer [timeout <!]]))

(enable-console-print!)

(def app-state (atom {:text "Hello, world!"}))

(defn -main []

  (om/root
    (fn [app owner]
      (reify om/IRender
        (render [_]
          (dom/h1 nil (:text app)))))
    app-state
    {:target (. js/document (getElementById "chrome-root"))})

  (go-loop [flag 1]
           (let [isOdd (odd? flag)]
           (swap! app-state assoc :text (if isOdd "Odd" "Even"))
           (<! (timeout 250))
           (recur (if isOdd 0 1))))

  (println "Running"))

(-main)

