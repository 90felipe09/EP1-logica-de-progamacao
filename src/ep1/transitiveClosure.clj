(ns ep1.transitiveClosure (:gen-class))
(require '[ep1.relationUtils :as relationUtils])

(defn trincas [elements]
  "Generates all triads of tuples [{a, b} {b, c} {a, c}] based on a set of elements, where a, b and c are all different"
  (let [triads (remove
                  (fn [item]
                    (or (= (item 0) (item 1)) (= (item 1) (item 2)) (= (item 2) (item 1))))
                  (for [ a elements, b (disj elements a), c (disj elements a b)]
                    [a b c]))]
    (map
      (fn [triad] [{(triad 0) (triad 1)}, {(triad 1) (triad 2)}, {(triad 0) (triad 2)}])
      triads)))

(defn missingTransitivity?
  "Verifies wether a transitivity triad belongs to a relation"
  [relation triad]
  (and
    (some #(= % (triad 0)) relation)
    (some #(= % (triad 1)) relation)
    (not (some #(= % (triad 2)) relation))))

(defn transitiveClosure [rel]
  "Receives a parsed relationship (set of hashmaps), returns the transitive closure"
  (let [rel-trincas (trincas (relationUtils/getRelationshipElements rel))]
    (loop
      [{fecho :fecho, continue :continue} {:fecho rel, :continue true}]
      (if continue
        (recur  (reduce
                  (fn [acc item]
                    (if (missingTransitivity? (acc :fecho) item)
                      { :fecho (into (acc :fecho) [(item 2)]),
                        :continue true }
                    ; else
                      { :fecho (acc :fecho),
                        :continue (or (acc :continue) false) })),
                  ; accumulator
                  { :fecho fecho,
                    :continue false },
                  ; initial collection
                  rel-trincas))
        ; else
        fecho))))