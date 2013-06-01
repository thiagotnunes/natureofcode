(ns vectors.example1.core
  (:require
   [quil.core                    :refer :all]
   [vectors.example1.ball-drawer :as ball-drawer]
   [vectors.example1.ball        :as ball]))

(def dimensions [400 400])

(defn setup []
  (smooth)
  (frame-rate 100)
  (set-state! :width (first dimensions)
              :height (second dimensions)))

(defn draw []
  (background 255)
  (ball-drawer/draw @ball/state)
  (ball/move! dimensions))

(defsketch example
  :title "Bouncing ball"
  :setup setup
  :draw draw
  :size [400 400])

(defn -main [& args]
  (example))
