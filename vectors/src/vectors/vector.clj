(ns vectors.vector
  (:require
   [clojure.contrib.math :as math]))

(defn plus [[x1 y1] [x2 y2]]
  [(+ x1 x2) (+ y1 y2)])

(defn sub [[x1 y1] [x2 y2]]
  [(- x1 x2) (- y1 y2)])

(defn mult [[x y] n]
  [(* x n) (* y n)])

(defn div [[x y] n]
  [(/ x n) (/ y n)])

(defn mag [[x y]]
  (math/sqrt (+ (* x x) (* y y))))

(defn normalize [v]
  (div v (mag v)))

(defn random2d []
  (normalize [(rand-int 10) (rand-int 10)]))