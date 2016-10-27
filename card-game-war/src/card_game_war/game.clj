(ns card-game-war.game
  (:require [clojure.core.match :refer [match]]))

(def suits [:spade :club :diamond :heart])
(def ranks [2 3 4 5 6 7 8 9 10 :jack :queen :king :ace])
(def cards
  (for [suit suits
        rank ranks]
    [suit rank]))

(defn compare-index [val1 val2 reference]
  (let [idx1 (.indexOf reference val1)
        idx2 (.indexOf reference val2)]
    (cond (< idx1 idx2) :lt
          (= idx1 idx2) :eq
          :else :gt)))

(defn play-round [[r1 s1] [r2 s2]]
  (match [(compare-index r1 r2 ranks) (compare-index s1 s2 suits)]
         [:gt _] :p1
         [:eq :gt] :p1
         :else :p2))

(defn play-game [[c1 & c1s :as player1-cards]
                 [c2 & c2s :as player2-cards]]
  (match
   [player1-cards player2-cards]
   [[] _] :p2
   [_ []] :p1

   :else
   (case (play-round c1 c2)
     :p1 (recur (conj (apply vector c1s) c1 c2)
                (apply vector c2s))
     :p2 (recur (apply vector c1s)
                (conj (apply vector c2s) c2 c1)))))
