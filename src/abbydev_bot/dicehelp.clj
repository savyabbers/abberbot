(ns abbydev-bot.dicehelp
  (:require [org.fversnel.dnddice.core :as dice]))

(def roll-help "use /roll 2d6 to roll two six sided dice")

(def dice-help (str "/roll string - roll dice using dice notation, "
                 "if no notation is given. 2d6 will be used\n"
                 "/rollhelp - get some example on how to use /roll"))

(defn dice-limiter?
  [{count :die-count sides :sides}]
  (if (and (< count 101) (< sides 101))
    true
    nil))

(defn check-dice [dice]
  (if (contains? dice :die-count)
    dice
    (conj dice {:die-count 1})))

(defn roll [arg]
  (let [user-dice (try
              (dice/parse arg)
              (catch Exception e nil))]
    (if user-dice
      (do
        (if (dice-limiter? (check-dice user-dice))
          (dice/die-rolls-to-str 10 (dice/roll user-dice))
          "Sorry, The limits for the dice are 100d100"))
      "Error parsing. use /rollhelp for info")))

