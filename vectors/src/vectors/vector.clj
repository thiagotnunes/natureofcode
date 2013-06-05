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
  (let [magnitude (mag v)]
    (if (= magnitude 0)
      v
      (div v magnitude))))

(defn random2d []
  (let [limit 20
        x (- (rand-int limit) 10)
        y (- (rand-int limit) 10)]
    (normalize [x y])))