(ns abbydev-bot.core
  (:require [morse.handlers :as h]
            [morse.api :as t]
            [morse.polling :as p]
            [clojure.string :as cstr]
            [clojure.core.async :refer [<!!]]
            [abbydev-bot.dicehelp :as dh]
            [abbydev-bot.catpics :refer [get-cat-pic cat-help]])
  (:gen-class))

(def token (System/getenv "TELTOKEN"))
(def help-text (str "/help - show this!\n"
                 cat-help
                 dh/dice-help))

(h/defhandler handler
  (h/command-fn "start" (fn [{{id :id :as chat} :chat}]
                          ;(println "abbersbot joined the chat!")
                          (t/send-text token id "Hello there! I'm Abberbot\n/help for my commands!")))
  (h/command "help" {{id :id :as chat} :chat}
    (t/send-text token id help-text))
  
  (h/command "roll" {{id :id :as chat} :chat text :text}
    ;(println (second (str/split text #" ")))
    (let [user-roll (second (cstr/split text #" "))]
      (if (cstr/blank? user-roll)
        (t/send-text token id (dh/roll "2d6"))
        (t/send-text token id (dh/roll user-roll)))))

  (h/command "cat" {{id :id :as chat} :chat}
    (let [catpic (get-cat-pic)]
      (if catpic
        (t/send-photo token id catpic)
        (t/send-text token id "Cat is hiding! (Error:)"))))

  (h/command "rollhelp" {{id :id :as chat} :chat}
    (t/send-text token id dh/roll-help))
  
  (h/message {{id :id} :chat msg :text}
    ;(println id "::" msg)
    (when (cstr/includes? (cstr/lower-case msg) "ping")
      (t/send-text token id "pong"))))

(defn -main
  "start bot"
  [& args]
  (when (cstr/blank? token)
    (println "NO TOKEN IN ENV 'TELTOKEN'")
    (System/exit 1))
  
  (println "strarting bot...")
  (<!! (p/start token handler {:timeout 10})))

