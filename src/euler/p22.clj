;Problem 22: Name Scores
;
;Using names.txt (right click and 'Save Link/Target As...'), a 46K text file containing over five-thousand first names, begin by sorting it into alphabetical order. Then working out the alphabetical value for each name, multiply this value by its alphabetical position in the list to obtain a name score.
;
;For example, when the list is sorted into alphabetical order, COLIN, which is worth 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list. So, COLIN would obtain a score of 938 × 53 = 49714.
;
;What is the total of all the name scores in the file?

(ns euler.p22
  (:require [clojure.string :as str]))

(defn- name-score
  [name]
  (->>
    name
    (map int)
    (map #(- % 64))
    (apply +)))

(defn get-name-scores
  []
  (let
   [names (->
    (slurp "resources/p022_names.txt")
    (str/split #","))]
    (->>
      names
      (sort)
      (map (partial drop 1))
      (map drop-last)
      (map name-score)
      (interleave (->>
                    (count names)
                    (inc)
                    (range)
                    (drop 1)))
      (partition 2)
      (map (partial apply *))
      (apply +))
   ))


