(ns distribution.walker
  (:require
   [quil.core :as q]))

(def width 640)
(def height 400)
(def walker (atom {:x (/ width 2) :y (/ height 2) :diam 5}))

(defn random-direction []
  (let [choice (rand-int 4)]
    (cond
     (= choice 0) (swap! walker update-in [:x] inc)
     (= choice 1) (swap! walker update-in [:x] dec)
     (= choice 2) (swap! walker update-in [:y] inc)
     (= choice 3) (swap! walker update-in [:y] dec))
    (q/ellipse (:x @walker) (:y @walker) (:diam @walker) (:diam @walker))))

(defn random-x-y []
  (let [x (-> 3 rand-int dec)
        y (-> 3 rand-int dec)]
    (swap! walker update-in [:x] #(+ % x))
    (swap! walker update-in [:y] #(+ % y))
    (q/ellipse (:x @walker) (:y @walker) (:diam @walker) (:diam @walker))))

(defn probability []
  (let [choice (rand)]
    (if (< choice 0.4)
      (swap! walker update-in [:x] inc)
      (if (< choice 0.6)
        (swap! walker update-in [:x] dec)
        (if (< choice 0.8)
          (swap! walker update-in [:y] dec)
          (swap! walker update-in [:y] inc))))))

(defn setup []
  (q/smooth)
  (q/frame-rate 60)
  (q/background 200))

(defn draw []
  (q/stroke 0)
  (q/stroke-weight 1)
  (q/fill 0)
  (random-direction))

(q/defsketch example
  :title "Random walker"
  :setup setup
  :draw draw
  :size [width height])
