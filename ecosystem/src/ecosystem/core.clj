(ns ecosystem.core
  (:require
   [quil.core :refer :all]
   
   [ecosystem.fish :as fish]))

(def dimensions [400 400])

(defn setup []
  (smooth)
  (frame-rate 60)
  (set-state! :width (first dimensions)
              :height (second dimensions)))

(defn draw []
  (let [mouse [(mouse-x) (mouse-y)]]
    (background 255)
    (fish/move! dimensions)
    (ellipse (-> fish/state deref :position first)
             (-> fish/state deref :position second)
             10
             10)))

(defsketch ecosystem
  :title "Ecosystem"
  :setup setup
  :draw draw
  :size [400 400])

(defn -main [& args]
  (ecosystem))
