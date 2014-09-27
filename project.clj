(defproject cljs-mies "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [org.clojure/clojurescript "0.0-2311"]]

  :plugins [[lein-cljsbuild "1.0.4-SNAPSHOT"]
            [lein-npm "0.4.0"]]

  :source-paths ["src"]

  :node-dependencies [express "4.9.5"
                      thicket "0.3.5"]

  :cljsbuild {
    :builds [{:id "cljs-mies-client"
              :source-paths ["src/cljs/client", "src/cljs/common"]
              :compiler {
                          :output-to "build/client/cljs_mies_client.js"
                          :output-dir "build/client/out"
                          :optimizations :none
                          :source-map true}}

             {:id "cljs-mies-server"
              :source-paths ["src/cljs/server", "src/cljs/common"]
              :compiler {
                          :target :nodejs
                          :output-to "build/server/cljs_mies_server.js"
                          :output-dir "build/server/out"
                          :optimizations :simple
                          :source-map "build/server/cljs_mies_server.js.map"}}]})
