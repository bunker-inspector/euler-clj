(ns euler.core)

;Problem 21: Amicable Numbers
;
;Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
;If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair and each of a and b are called amicable numbers.
;
;For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; therefore d(220) = 284. The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.
;
;Evaluate the sum of all the amicable numbers under 10000.

(defn divisors
  [x]
  (->>
    (/ x 2)
    (inc)
    (range)
    (drop 1)
    (filter #(= 0 (mod x %)))
    ))

(defn- sum-divisors
  [x]
  (apply + (divisors x)))

(def sum-divisors* (memoize sum-divisors))

(let [r (drop 1 (range 10000))]
  (->>
    r
    (interleave (map sum-divisors* r))
    (partition 2)
    (filter (fn [[a b]] (not= a b)))
    (filter (fn [[sum value]] (= value (sum-divisors* sum))))
    (map sort)
    (set)
    (map (partial apply +))
    (apply +)
    ))

