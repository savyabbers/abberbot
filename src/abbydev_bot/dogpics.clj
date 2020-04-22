(ns abbydev-bot.dogpics
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]))

(def dogurl "https://api.thedogapi.com/v1/images/search?order=Rand")
(def dog-help "/dog - bark!\n/doggif - moving Barks!\n")


(defn get-dog-pic []
  (try
    (:url (first (json/read-str (:body (client/get (str dogurl
                                                        "&mine_types=png,jpg")))
                                :key-fn keyword)))
    (catch Exception e nil)))

(defn get-dog-gif []
  (try
    (:url (first (json/read-str (:body (client/get (str dogurl
                                                        "&mime_types=gif")))
                                :key-fn keyword)))
    (catch Exception e nil)))
