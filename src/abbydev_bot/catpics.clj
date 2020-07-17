(ns abbydev-bot.catpics
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]))

(def caturl "https://api.thecatapi.com/v1/images/search?order=Rand")
(def cat-help "/cat - meow\n/catgif - meowies\n")


(defn get-cat-pic []
  (try
    (:url (first (json/read-str (:body (client/get (str caturl
                                                        "&mime_type=png,jpg")))
                                :key-fn keyword)))
    (catch Exception e nil)))

(defn get-cat-gif []
  (try
    (:url (first (json/read-str (:body (client/get (str caturl
                                                        "&mime_types=gif&size=full")))
                                :key-fn keyword)))
    (catch Exception e nil)))
