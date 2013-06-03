(ns vectors.example3.core
  (:require
   [quil.core      :refer :all]
   [vectors.vector :as vector]))

(def dimensions [400 400])

(defn setup []
  (smooth)
  (frame-rate 100)
  (set-state! :width (first dimensions)
              :height (second dimensions)))

(defn draw []
  (let [center [(/ (first dimensions) 2)
                (/ (second dimensions) 2)]
        mouse [(mouse-x) (mouse-y)]
        mouse-relative-to-center (vector/sub mouse center)
        scaled-mouse (vector/div mouse-relative-to-center 2)]
    (background 255)
    (translate (first center) (second center))
    (line 0 0 (first scaled-mouse) (second scaled-mouse))))

(defsketch example
  :title "Line follow"
  :setup setup
  :draw draw
  :size [400 400])

(defn -main [& args]
  (example))
