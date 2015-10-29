(ns wonderland-number.finder)

(defn isWonderNum? [n]
  (let [digit-set #(set (str %))
        digits (digit-set n)
        mult-digits (map (comp digit-set (partial * n)) [2 3 4 5 6])]
    (every? (partial = digits) mult-digits)))

(defn wonderland-number []
  (->> (range 100000 999999)
       (filter isWonderNum?)
       first))
