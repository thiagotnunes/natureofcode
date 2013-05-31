(ns bouncing-ball.ball-drawer
  (:require
   [quil.core :refer :all]))

(def drawing {:stroke 1
              :stroke-weight 1
              :fill 0
              :diameter 50})

(defn draw [ball]
  (let [[x y z] (:position ball)
        diameter (:diameter drawing)]

    (smooth)
    (sphere-detail diameter)
    (translate x y z)
    (sphere diameter)))