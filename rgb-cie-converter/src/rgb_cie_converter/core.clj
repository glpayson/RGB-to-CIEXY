(ns rgb-cie-converter.core
  (:require [clojure.math.numeric-tower :as math]))

(defn normalize-color [c]
  (/ c 255.0))

(defn enhance-color [c]
  (if (> c 0.04045)
    (math/expt
      (/ (+ c 0.055) 1.055)
      2.4)
    (/ c 12.92)))

(defn to-xyz [rgb]
  (let [[r g b] rgb]
    [(+
       (* r 0.649926)
       (* g 0.103455)
       (* b 0.197109))
     (+
       (* r 0.234327)
       (* g 0.743075)
       (* b 0.022598))
     (+
       (* r 0.000000)
       (* g 0.053077)
       (* b 1.035763))]))

(defn to-xy [xyz]
  (let [[x y z] xyz]
    (if (= (+ x y z) 0.0)
      [1 1]
      [(/ x (+ x y z))
       (/ y (+ x y z))])))

(defn rgb-to-cie-space [r g b]
  (->>
    (map normalize-color [r g b])
    (map enhance-color)
    (to-xyz)
    (to-xy)))

