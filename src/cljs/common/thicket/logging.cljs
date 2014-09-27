(ns thicket.logging)

(def ^:private t (js/require "thicket"))
(def ^:private Logger (.c t "logger"))
(def ^:private ConsoleLogAppender (.c t "appenders/console-log"))
(defn ^:private log-level [level] (aget Logger "Level" level))

(defn logger [name]
  (.create Logger name))

(defn set-log-level! [logger level]
  (.setLogLevel logger level))

(defn add-appender! [logger appender]
  (.addAppender logger appender))

(defn root-set-log-level! [level]
  (set-log-level! (.root Logger) (log-level level)))

(defn root-add-appender! [appender]
  (add-appender! (.root Logger) appender))

(defn console-log-appender [] (ConsoleLogAppender.))
