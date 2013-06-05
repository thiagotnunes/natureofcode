(ns vectors.example6.ball
  (:require
   [vectors.vector :as vector]))

(def state (atom {:position [100 100]
                  :speed [1 3.3]
                  :acceleration [0.01 0.01]}))

(def speed-limit 100)

(defn- move [position speed [width height]]
  (let [[x y] (vector/plus position speed)
        next-x (if (> x width) 0 x)
        next-y (if (> y height) 0 y)]
    [next-x next-y]))

(defn- next-speed [speed acceleration]
  (let [accelerated-speed (+ speed acceleration)]
    (if (>= accelerated-speed speed-limit)
      speed
      accelerated-speed)))

(defn- refresh-speed [[xSpeed ySpeed] [xAcc yAcc]]
  [(next-speed xSpeed xAcc)
   (next-speed ySpeed yAcc)])

(defn move! [dimensions]
  (let [current-state @state
        position (:position current-state)
        speed (:speed current-state)
        acceleration (:acceleration current-state)
        next-position (move position speed dimensions)
        next-speed (refresh-speed speed acceleration)]
    (swap! state conj {:position next-position
                       :speed next-speed})))