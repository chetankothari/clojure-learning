(ns mars-rover.core-test
  (:require [clojure.test :refer :all]
            [mars-rover.core :refer :all]))

(deftest test-navigate
  (testing "Moves a grid forward"
    (is (= [1 0 "E"] (navigate [0 0 "E"] "M"))))

  (testing "Moves the mars rover from [0 0 N] to [1 1 E] for MRM"
    (is (= [1 1 "E"] (navigate [0 0 "N"] "MRM"))))

  (testing "Moves the mars rover from [0 2 W] to [1 1 S] for LMLMR"
    (is (= [1 1 "S"] (navigate [0 2 "W"] "LMLMR"))))

  (testing "Moves the mars rover from [0 2 W] to [1 1 S] for RRMRM"
    (is (= [1 1 "S"] (navigate [0 2 "W"] "RRMRM"))))

  (testing "Moves the mars rover from [0 2 W] to [5 -2 N] for LMMMMRRRMMMMML"
    (is (= [5 -2 "N"] (navigate [0 2 "W"] "LMMMMRRRMMMMML"))))

  (testing "Moves the mars rover from [0 2 W] to [5 -2 N] for RRMMMMMRMMMMLL"
    (is (= [5 -2 "N"] (navigate [0 2 "W"] "RRMMMMMRMMMMLL"))))

  (testing "Changes the direction of the rover for North to East"
    (is (= [0 0 "E"] (navigate [0 0 "N"] "R")))))


(deftest test-turn-left
  (testing "Changes the direction of the rover from Notrh to West"
    (is (= \W (turn-left \N))))

  (testing "Changes the direction of the rover from South to East"
    (is (= \E (turn-left \S))))

  (testing "Changes the direction of the rover from East to North"
    (is (= \N (turn-left \E))))

  (testing "Changes the direction of the rover for West to South"
    (is (= \S (turn-left \W))))

  (testing "Returns the same direction if the give direction is invalid"
    (is (= \P (turn-left \P)))))

(deftest test-turn-right
  (testing "Changes the direction of the rover from Notrh to East"
    (is (= \E (turn-right \N))))

  (testing "Changes the direction of the rover from South to West"
    (is (= \W (turn-right \S))))

  (testing "Changes the direction of the rover from East to South"
    (is (= \S (turn-right \E))))

  (testing "Changes the direction of the rover from West to North"
    (is (= \N (turn-right \W))))

  (testing "Returns the same direction if the give direction is invalid"
    (is (= \R (turn-right \R)))))

(deftest test-change-position
  (testing "Increments the y-cordinates if the direction is North"
    (is (= [1 3] (change-position \N [1 2]))))

  (testing "Increments the x-cordinates if the direction is East"
    (is (= [5 10] (change-position \E [4 10]))))

  (testing "Decrements the y-cordinates if the direction South"
    (is (= [3 4] (change-position \S [3 5]))))

  (testing "Decrements the x-cordinates if the direction West"
    (is (= [-1 1] (change-position \W [0 1])))))
