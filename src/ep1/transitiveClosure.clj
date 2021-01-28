(ns ep1.transitiveClosure (:gen-class))
(require '[ep1.relationUtils :as relationUtils])

(defn triads [elements]
  "Generates all triads of tuples [{a, b} {b, c} {a, c}] based on a set of elements, where a, b and c are all different"
  (let [triples (remove
                  (fn [item]
                    (or (= (item 0) (item 1)) (= (item 1) (item 2)) (= (item 2) (item 1))))
                  (for [ a elements, b (disj elements a), c (disj elements a b)]
                    [a b c]))]
    (map
      (fn [triple] [{(triple 0) (triple 1)}, {(triple 1) (triple 2)}, {(triple 0) (triple 2)}])
      triples)))

(defn missingTransitivity?
  "Verifies wether a transitivity triad belongs to a relation. The first and second elements must belong, the third must not."
  [relation triad]
  (and
    (some #(= % (triad 0)) relation)
    (some #(= % (triad 1)) relation)
    (not (some #(= % (triad 2)) relation))))

(defn _transitiveClosure
  "Receives a relation, a continue flag and a set of triads to test with, and recursively includes missing transitivity paths"
  [{relation :relation, continue :continue}, triads]
  (if continue
    (_transitiveClosure
      ; {relation:, continue:}
      (reduce (fn [acc, item]
                (if (missingTransitivity? (acc :relation) item)
                  { :relation (into (acc :relation) [(item 2)]),
                    :continue, true}
                ; else
                  { :relation (acc :relation),
                    :continue (or (acc :continue) false) })),
        ; initial accumulator
        { :relation relation,
          :continue false },
        ; initial collection
        triads),
      ; triads
      triads)
    ; else
    relation))

(defn transitiveClosure [rel]
  "Receives a parsed relationship (set of hashmaps), returns the transitive closure"
  (_transitiveClosure {:relation rel, :continue true}, (triads (relationUtils/getRelationshipElements rel))))