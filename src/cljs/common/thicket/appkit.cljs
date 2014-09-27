(ns thicket.appkit)

(def t (js/require "thicket"))

(defn noop)

(defn bootstrap
  ([] (bootstrap noop noop))
  ([up] (bootstrap up noop))
  ([up down]
   (let [Bootstrap (.c t "bootstrapper")
         bootstrapper (Bootstrap. #js {:up up :down down})]

     (.bootstrap bootstrapper))))
