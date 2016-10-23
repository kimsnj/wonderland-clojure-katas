(ns doublets.solver
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]
            [clojure.set :as set]))

(def words (-> "words.edn"
               (io/resource)
               (slurp)
               (read-string)))

(defn pairs
  "Extracts pairs of words of same length from a list."
  [words length]
  (for [w1 words
        w2 words
        :when (= length (count w1) (count w2))]
    [w1 w2]))

(defn neighboors? [[w1 w2]]
  "Two words are neighboors if there is a sigle modification
  (expects words of same length)"
  (->> (map not= w1 w2)
       (filter identity)
       (= [true])))

(defn neighboor-graph [words length]
  (reduce (fn [acc [a b]]
            (if (acc a)
              (update acc a conj b)
              (assoc acc a #{b})))
          {}
          (filter neighboors? (pairs words length))))

(defn search [graph visited path dest src]
  (let [neighboors (graph src)
        next-edges (set/difference neighboors visited)]
    (cond
      (empty? next-edges) []
      (next-edges dest) (conj path src dest)
      :else (some (partial search
                     graph
                     (set/union visited next-edges)
                     (conj path src)
                     dest)
                  next-edges))))

(defn doublets [word1 word2]
  (let [g (neighboor-graph words (count word1))]
    (search g #{} [] word2 word1)))
