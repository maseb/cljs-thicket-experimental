cljs-thicket-experimental
=========================

To play:

  1. Install [node](http://nodejs.org/)
  2. [Install lein](http://leiningen.org/#install)
  3. Check out project, cd in.
  4. Run `lein npm install` to install NPM modules 
  5. Install experimental 0.3.6 version of thicket ([https://github.com/maseb/thicket/tree/wip-browserify](https://github.com/maseb/thicket/tree/wip-browserify))
  6. Run `lein cljsbuild once` to build JS sources
  7. Run `node build/server/server.js --configurationFiles=./configuration/global.json,./configuration/development.json --scopes=global,development` to start server
  8. Open browser, go to [http://localhost:3000](http://localhost:3000)
