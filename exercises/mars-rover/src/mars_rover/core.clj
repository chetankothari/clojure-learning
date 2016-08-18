(ns mars-rover.core)

(defn turn-left
  [direction]
  (case direction
    \N \W
    \S \E
    \E \N
    \W \S
    direction))

(defn turn-right
  [direction]
  (case direction
    \N \E
    \S \W
    \E \S
    \W \N
    direction))

(defn change-position
  [direction [x-cod y-cod]]
  (case direction
    \N [x-cod (inc y-cod)]
    \S [x-cod (dec y-cod)]
    \E [(inc x-cod) y-cod]
    \W [(dec x-cod) y-cod]
    ))

(defn navigate
  [[x-cod y-cod direction] [instruction & rest]]
  (if (not (empty? (str instruction)))
    (navigate
      (case instruction
        \L [x-cod y-cod (str (turn-left (first direction)))]
        \R [x-cod y-cod (str (turn-right (first direction)))]
        \M (let [[n-x-cod n-y-cod] (change-position (first direction) [x-cod y-cod])] [n-x-cod n-y-cod direction])
        [x-cod y-cod direction])
      rest)
    [x-cod y-cod direction]))


