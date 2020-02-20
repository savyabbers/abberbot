(ns abbydev-bot.dicehelp
  (:require [org.fversnel.dnddice.core :as dice]))

(def roll-help "use /roll 2d6 to roll two six sided dice")

(defn dice-limiter?
  [{count :die-count sides :sides}]
  (if (and (< count 101) (< sides 101))
    true
    nil))

(defn roll [arg]
  (let [user-dice (try
              (dice/parse arg)
              (catch Exception e nil))]
    (if user-dice
      (do
        (if (dice-limiter? user-dice)
          (dice/die-rolls-to-str 10 (dice/roll user-dice))
          "Sorry, The limits for the dice are 100d100"))
      "Error parsing. use /rollhelp for info")))

