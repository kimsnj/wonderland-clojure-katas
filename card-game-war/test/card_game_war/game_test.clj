(ns card-game-war.game-test
  (:require [clojure.test :refer :all]
            [card-game-war.game :refer :all]))


;; fill in  tests for your game
(deftest test-play-round
  (testing "the highest rank wins the cards in the round"
    (is (= :p1 (play-round [10 :jack] [7 :club])))
    (is (= :p2 (play-round [5 :heart] [9 :spade]))))

  (testing "queens are higher rank than jacks"
    (is (= :p1 (play-round [:queen :spade] [:jack :heart]))))

  (testing "kings are higher rank than queens"
    (is (= :p2 (play-round [:queen :spade] [:king :spade]))))

  (testing "aces are higher rank than kings"
    (is (= :p1 (play-round [:ace :space] [:king :heart]))))

  (testing "if the ranks are equal, clubs beat spades"
    (is (= :p2 (play-round [7 :spade] [7 :club]))))

  (testing "if the ranks are equal, diamonds beat clubs"
    (is (= :p1 (play-round [9 :diamond] [9 :club]))))

  (testing "if the ranks are equal, hearts beat diamonds"
    (is (= :p2 (play-round [4 :diamond] [4 :heart])))))

(deftest test-play-game
  (testing "the player loses when they run out of cards"
    (is (= :p1 (play-game [[2 :diamond]] [])))
    (is (= :p2 (play-game [] [[2 :diamond]])))
    (is (= :p1 (play-game [[9 :diamond]] [[5 :spade]])))

    (is (= :p2 (play-game [[9 :diamond] [7 :spade] [:jack :club]]
                          [[10 :club] [5 :spade] [:king :heart]])))))

