(ns vectors.example8.ball
  (:require
   [vectors.vector :as vector]))

(def state (atom {:position [100 100]
                  :speed [1 3.3]}))

(def speed-limit 3)

(defn next-movement [coord boundary]
  (if (> coord boundary)
    0
    (if (< coord 0)
      boundary
      coord)))

(defn- move [position speed [width height]]
  (let [[x y] (vector/plus position speed)
        next-x (next-movement x width)
        next-y (next-movement y height)]
    [next-x next-y]))

(defn- next-speed [speed acceleration]
  (let [accelerated-speed (+ speed acceleration)]
    (if (or (>= accelerated-speed speed-limit)
            (<= accelerated-speed (- speed-limit)))
      speed
      accelerated-speed)))

(defn- refresh-speed [[xSpeed ySpeed] [xAcc yAcc]]
  [(next-speed xSpeed xAcc)
   (next-speed ySpeed yAcc)])

(defn- refresh-acceleration [position mouse]
  (let [direction (vector/sub mouse position)
        normalized-direction (vector/normalize direction)
        direction-magnitude (vector/mag direction)
        factor 10]
    (vector/div normalized-direction (/ factor direction-magnitude))))

(defn move! [dimensions mouse]
  (let [current-state @state
        position (:position current-state)
        speed (:speed current-state)
        acceleration (:acceleration current-state)
        next-position (move position speed dimensions)
        next-acceleration (refresh-acceleration next-position mouse)
        next-speed (refresh-speed speed next-acceleration)]
    (swap! state conj {:position next-position
                       :speed next-speed})))
