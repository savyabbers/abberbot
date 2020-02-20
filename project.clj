(defproject abbydev-bot "0.4.1"
  :description "A telegram bot developed by @savyabby"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [morse "0.4.3"]
                 [org.clojure/core.async "0.7.559"]
                 [org.fversnel/dnddice "3.0.3"]
                 [clj-http "3.10.0"]
                 [org.clojure/data.json "0.2.7"]]
                 
  :main ^:skip-aot abbydev-bot.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
