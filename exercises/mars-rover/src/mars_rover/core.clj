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
  (case instruction
    \L [xCod yCod (turn-left direction)]
    \R [xCod yCod (turn-right direction)]
    \M (let [[nXCod nYCod] (change-position direction [xCod yCod])] (navigate [nXCod nYCod direction] rest))
    [xCod yCod direction]))


