(ns magic-square.puzzle
  (:require [org.clojure/math.combinatorics :refer [permutations]]))

(def values [1.0 1.5 2.0 2.5 3.0 3.5 4.0 4.5 5.0])
(def sample [[1.0 1.5 2.0] [2.5 3.0 3.5] [4.0 4.5 5.0]])

(defn is-magic? [m]
  (let [cols (apply map vector m)
        get-in-m #(get-in m %)
        d1 (map get-in-m [[0 0] [1 1] [2 2]])
        d2 (map get-in-m [[0 2] [1 1] [2 0]])]
    (->> (concat m cols [d1 d2])
         (map (partial apply +))
         (apply =))))

(defn matrics [values]
  (let [perm (per)]))

(defn magic-square [values]
  [[1.0 1.5 2.0]
   [2.5 3.0 3.5]
   [4.0 4.5 5.0]])
