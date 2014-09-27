(ns thicket.appkit
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs.nodejs :as nodejs]
            [cljs.core.async :refer [put! chan <! >!]]))

(def t (js/require "thicket"))

(defn noop [])

(defn bootstrap
  ([] (bootstrap noop noop))
  ([up] (bootstrap up noop))
  ([up down]
   (let [Bootstrap (.c t "bootstrapper")
         bootstrapper (Bootstrap. #js {:up up :down down})
         c (chan 1)]

     (go (.bootstrap bootstrapper (fn [err container]
                                    (put! c [err container]))))
     c)))
