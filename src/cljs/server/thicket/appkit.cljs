(ns thicket.appkit
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs.nodejs :as nodejs]
            [cljs.core.async :refer [put! chan <! >!]]))

(def ^:private t (js/require "thicket"))

(defn- noop [])

(defn- wire-callback [bootstrapper c]
  (.. (.bootstrap bootstrapper)
      (then (fn [container]
              (put! c container)))))

(defn bootstrap
  ([] (bootstrap noop noop))
  ([up] (bootstrap up noop))
  ([up down]
   (let [Bootstrap (.c t "bootstrapper")
         bootstrapper (Bootstrap. #js {:up up :down down})
         c (chan 1)]
     (wire-callback bootstrapper c)
     c)))
