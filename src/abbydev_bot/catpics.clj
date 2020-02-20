(ns abbydev-bot.catpics
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]))

(def caturl "https://api.thecatapi.com/v1/images/search?order=Rand")


(defn get-cat-pic []
  (try
    (:url (first (json/read-str (:body (client/get caturl)) :key-fn keyword)))
    (catch Exception e nil)))