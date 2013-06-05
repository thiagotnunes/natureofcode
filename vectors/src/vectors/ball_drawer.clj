(ns vectors.ball-drawer
  (:require
   [quil.core :refer :all]))

(def drawing {:stroke 1
              :stroke-weight 1
              :fill 0
              :diameter 50})

(defn draw [ball]
  (let [[x y] (:position ball)
        diameter (:diameter drawing)]

    (stroke (:stroke drawing))
    (stroke-weight (:stroke-weight drawing))
    (fill (:fill drawing))
    (ellipse x y diameter diameter)))