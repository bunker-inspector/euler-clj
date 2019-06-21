;Problem 24: Lexographic Permutations
;
;A permutation is an ordered arrangement of objects. For example, 3124 is one possible permutation of the digits 1, 2, 3 and 4.
;If all of the permutations are listed numerically or alphabetically, we call it lexicographic order. The lexicographic permutations of 0, 1 and 2 are:
;
;012   021   102   120   201   210
;
;What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?

(ns euler.p24
  (:require [clojure.math.combinatorics :as c])
  (:require [clojure.string :as s]))

(def digits "0123456789")

(nth (->>
      digits
      (c/permutations)
      (map (partial s/join ""))
      (sort))
      (dec 1000000))
