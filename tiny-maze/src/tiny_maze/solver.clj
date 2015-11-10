(ns tiny-maze.solver)

(def moves [[1 0] [0 1] [-1 0] [0 -1]])

(defn is-possible-position [maze pos]
  ((complement contains?)
     #{:x 1 nil}
     (get-in maze pos)))

(defn possible-positions [maze pos]
  (->> moves
       (map (partial map + pos))
       (filter (partial is-possible-position maze))))

(defn visit-maze [maze pos]
  (assoc-in maze pos :x))

(defn is-exit-found [maze [x y]]
  (= ((maze x) y) :E))

(def first-truthy
  (partial some identity))

(defn walk [maze pos]
  (let [new-maze (visit-maze maze pos)
        next-moves (possible-positions maze pos)]
    (cond (is-exit-found maze pos) new-maze
          (empty? next-moves) nil
          :else (first-truthy (map (partial walk new-maze) next-moves)))))

(defn solve-maze [maze]
  (walk maze '(0 0)))
