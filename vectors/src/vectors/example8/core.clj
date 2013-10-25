(ns vectors.example8.core
  (:require
   [quil.core             :refer :all]
   [vectors.ball-drawer   :as ball-drawer]
   [vectors.example8.ball :as ball]))

(def dimensions [400 400])

(defn setup []
  (smooth)
  (frame-rate 100)
  (set-state! :width (first dimensions)
              :height (second dimensions)))

(defn draw []
  (let [mouse [(mouse-x) (mouse-y)]]
    (background 255)
    (ball-drawer/draw @ball/state)
    (ball/move! dimensions mouse)))

(defsketch example
  :title "Ball acceleration towards the mouse"
  :setup setup
  :draw draw
  :size [400 400])

(defn -main [& args]
  (example))