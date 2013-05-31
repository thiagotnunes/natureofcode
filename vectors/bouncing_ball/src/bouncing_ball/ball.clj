(ns bouncing-ball.ball)

(def state (atom {:position [10 70]
                  :speed [4 3.3]}))

(defn- move [[x y] [xSpeed ySpeed]]
  [(+ x xSpeed) (+ y ySpeed)])

(defn- next-speed [coord boundary speed]
  (if (or (> coord boundary) (< coord 0))
    (- speed)
    speed))

(defn- refresh-speed [[x y] [xSpeed ySpeed] width height]
  [(next-speed x width xSpeed)
   (next-speed y height ySpeed)])

(defn move! [[width height]]
  (let [current-state @state
        position (:position current-state)
        speed (:speed current-state)
        next-position (move position speed)
        next-speed (refresh-speed next-position speed width height)]
    (swap! state conj {:position next-position
                       :speed next-speed})))