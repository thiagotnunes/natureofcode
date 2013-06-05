(ns vectors.example4.core
  (:require
   [quil.core      :refer :all]
   [vectors.vector :as vector]))

(def dimensions [400 400])

(defn setup []
  (smooth)
  (frame-rate 100)
  (set-state! :width (first dimensions)
              :height (second dimensions)))

(defn- draw-line [center mouse]
  (push-matrix)
  (translate (first center) (second center))
  (line 0 0 (first mouse) (second mouse))
  (pop-matrix))

(defn- draw-magnitude [v]
  (let [magnitude (vector/mag v)]
    (push-matrix)
    (rect 0 0 magnitude 10)
    (pop-matrix)))

(defn draw []
  (let [center [(/ (first dimensions) 2)
                (/ (second dimensions) 2)]
        mouse [(mouse-x) (mouse-y)]
        mouse-relative-to-center (vector/sub mouse center)]
    (background 255)
    (draw-line center mouse-relative-to-center)
    (draw-magnitude mouse-relative-to-center)))

(defsketch example
  :title "Vector magnitude"
  :setup setup
  :draw draw
  :size [400 400])

(defn -main [& args]
  (example))
