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
  [direction [xCod yCod]]
  (case direction
    \N [xCod (inc yCod)]
    \S [xCod (dec yCod)]
    \E [(inc xCod) yCod]
    \W [(dec xCod) yCod]
    ))

(defn navigate
  [[xCod yCod direction] [instruction & rest]]
  (if (not (empty? (str instruction)))
    (navigate
      (case instruction
        \L [xCod yCod (str (turn-left (first direction)))]
        \R [xCod yCod (str (turn-right (first direction)))]
        \M (let [[nXCod nYCod] (change-position (first direction) [xCod yCod])] [nXCod nYCod direction])
        [xCod yCod direction])
      rest)
    [xCod yCod direction]))


