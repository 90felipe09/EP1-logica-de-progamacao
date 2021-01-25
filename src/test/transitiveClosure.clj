(ns test.transitiveClosure (:gen-class))
(require '[test.relationUtils :as relationUtils])

(defn trincas [elements]
  (remove
    (fn [item]
      (or (= (item 0) (item 1)) (= (item 1) (item 2)) (= (item 2) (item 1)))
    )
    (for [a elements
      b (disj elements a)
      c (disj elements a b)]
      [a b c])))

(defn transit [rel]
  (let [rel-trincas (map (fn [trinca]
                          [{(trinca 0) (trinca 1)} {(trinca 1) (trinca 2)} {(trinca 0) (trinca 2)}])
                      (trincas (relationUtils/getRelationshipElements rel)))]
    (loop [fecho-inicial rel, continue true]
      (if continue
        (let [{fecho :fecho, dirty :dirty}
          (reduce
              (fn [acc item]
                (if (and
                      (some #(= % (item 0)) (acc :fecho))
                      (some #(= % (item 1)) (acc :fecho))
                      (not (some #(= % (item 2)) (acc :fecho))))
                  {:fecho (into (acc :fecho) [(item 2)]), :dirty true}
                  {:fecho (acc :fecho), :dirty (or (acc :dirty) false)}))
              {:fecho fecho-inicial :dirty false} rel-trincas)]
            (recur fecho dirty))
          fecho-inicial))))