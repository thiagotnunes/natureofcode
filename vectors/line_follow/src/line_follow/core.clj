(ns line-follow.core
  (:require
   [quil.core :refer :all]))

(def dimensions [400 400])

(defn setup []
  (smooth)
  (frame-rate 100)
  (set-state! :width (first dimensions)
              :height (second dimensions)))

(defn- sub [v1 v2]
  [(- (first v1) (first v2))
   (- (second v1) (second v2))])

(defn- mult [v n]
  [(* (first v) n)
   (* (second v) n)])

(defn draw []
  (let [mouse [(mouse-x) (mouse-y)]
        center [(/ (first dimensions) 2) (/ (second dimensions) 2)]
        mouse-line (sub mouse center)
        resized-line (mult mouse-line 1/3)]
    (background 255)
    (translate (first center)
               (second center))
    (line 0 0 (first resized-line) (second resized-line))))

(defsketch example
  :title "Line follow"
  :setup setup
  :draw draw
  :size [400 400]
  :renderer :opengl)

(defn -main [& args]
    (example))
