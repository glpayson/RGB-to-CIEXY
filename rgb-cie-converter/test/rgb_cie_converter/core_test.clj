(ns rgb-cie-converter.core-test
  (:require [clojure.test :refer :all]
            [rgb-cie-converter.core :refer :all :as converter]))

;; Test values are from http://www.developers.meethue.com/documentation/hue-xy-values

(deftest rgb-to-cie-test
  (let [expected-x 0.29918957031149185
        expected-y 0.320790072802549
        r 239
        g 247
        b 255]
    (testing "Convert Alice Blue RGB to CIE."
      (is (= [expected-x expected-y]
             (converter/rgb-to-cie r g b))))))
