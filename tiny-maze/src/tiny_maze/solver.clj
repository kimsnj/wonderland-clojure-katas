(ns tiny-maze.solver)

(def moves [[1 0] [0 1] [-1 0] [0 -1]])

(defn is-possible-position [maze [x y]]
  ((complement contains?)
     #{:x 1 nil}
     (nth (nth maze x []) y nil)))

(defn possible-positions [maze pos]
  (->> moves
       (map (partial map + pos))
       (filter (partial is-possible-position maze))))

(defn visit-maze [maze [x y]]
  (assoc maze x (assoc (maze x) y :x)))

(defn is-exit-found [maze [x y]]
  (= ((maze x) y) :E))

(def first-truthy
  (comp first (partial filter identity)))

(defn walk [maze pos]
  (let [new-maze (visit-maze maze pos)
        next-moves (possible-positions maze pos)]
    (cond (is-exit-found maze pos) new-maze
          (empty? next-moves) nil
          :else (first-truthy (map (partial walk new-maze) next-moves)))))

(defn solve-maze [maze]
  (walk maze '(0 0)))
