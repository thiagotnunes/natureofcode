(ns bouncing-ball.ball)

(def state (atom {:position [100 100 0]
                  :speed [1 3 4]}))

(defn- move [[x y z] [xSpeed ySpeed zSpeed]]
  [(+ x xSpeed) (+ y ySpeed) (+ z zSpeed)])

(defn- next-speed [coord boundary speed]
  (if (or (> coord boundary) (< coord 0))
    (- speed)
    speed))

(defn- refresh-speed [[x y z] [xSpeed ySpeed zSpeed] [width height depth]]
  [(next-speed x (- width 25) xSpeed)
   (next-speed y (- height 25) ySpeed)
   (next-speed z depth zSpeed)])

(defn move! [dimensions]
  (let [current-state @state
        position (:position current-state)
        speed (:speed current-state)
        next-position (move position speed)
        next-speed (refresh-speed next-position speed dimensions)]
    (swap! state conj {:position next-position
                       :speed next-speed})))