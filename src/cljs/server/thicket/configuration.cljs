(ns thicket.configuration)

(def t (js/require "thicket"))

(defn resolve [scopes, configs]
  (let [ConfigurationMagic (.c t "configuration-magic")]
    (.resolveConfig ConfigurationMagic scopes configs)
    ))
