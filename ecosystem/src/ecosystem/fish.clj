(ns ecosystem.fish
  (:require
   [ecosystem.vector :as vector]))

(def speed-limit 0.4)

(def state (atom {:position [100 100]
                  :speed [0.2 0.2]
                  :acceleration [0 0]}))

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

(defn- refresh-acceleration [position dimensions]
  (-> (vector/random2d) (vector/mult 3)))

(defn- next-speed [speed acceleration]
  (let [accelerated-speed (+ speed acceleration)]
    (if (or (>= accelerated-speed speed-limit)
            (<= accelerated-speed (- speed-limit)))
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
        next-acceleration (refresh-acceleration next-position dimensions)
        next-speed (refresh-speed speed next-acceleration)]
    (swap! state conj {:position next-position
                       :speed next-speed
                       :acceleration next-acceleration})))
