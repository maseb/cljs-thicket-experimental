(defproject cljs-thicket-experimental "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [org.clojure/clojurescript "0.0-2311"]
                 [om "0.7.1"]]

  :plugins [[lein-cljsbuild "1.0.4-SNAPSHOT"]
            [org.bodil/lein-noderepl "0.1.11"]
            [lein-npm "0.4.0"]]

  :source-paths ["src"]

  :node-dependencies [express "4.9.5"
                      thicket "0.3.5"]

  :cljsbuild {
    :builds [{:id "client"
              :source-paths ["src/cljs/client", "src/cljs/common"]
              :compiler {
                          :output-to "build/client/client.js"
                          :output-dir "build/client/out"
                          :optimizations :none
                          :source-map true}}

             {:id "server"
              :source-paths ["src/cljs/server", "src/cljs/common"]
              :compiler {
                          :target :nodejs
                          :output-to "build/server/server.js"
                          :output-dir "build/server/out"
                          :optimizations :simple
                          :source-map "build/server/server.js.map"}}]})
