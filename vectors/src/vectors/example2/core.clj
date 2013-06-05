(ns vectors.example2.core
  (:require
   [quil.core :refer :all]))

(def dimensions [400 400])

(defn setup []
  (smooth)
  (frame-rate 100)
  (set-state! :width (first dimensions)
              :height (second dimensions)))

(defn draw []
  (let [center [(/ (first dimensions) 2)
                (/ (second dimensions) 2)]
        mouse [(mouse-x) (mouse-y)]]
    (background 255)
    (line (first center)
          (second center)
          (first mouse)
          (second mouse))))

(defsketch example
  :title "Line follow"
  :setup setup
  :draw draw
  :size [400 400])

(defn -main [& args]
  (example))
