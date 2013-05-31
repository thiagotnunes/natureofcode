(ns bouncing-ball.core
  (:require
   [quil.core          :refer :all]
   [bouncing-ball.ball-drawer :as ball-drawer]
   [bouncing-ball.ball :as ball]))

(def dimensions [323 200])

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
  :size [323 200])

(defn -main [& args]
  (example))
