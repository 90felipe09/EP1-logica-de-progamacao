(ns test.relationUtils (:gen-class))

(defn createRelationshipElement
  "Receives a 2-uple and returns a map"
  [row] 
  (hash-map (first row) (last row)))

(defn getMapper
  "From a relation element, get the mapper element"
  [relation]
  (first (keys relation))
  )

(defn getMapped
  "From a relation element, get the mapped element"
  [relation]
  (first (vals relation))
  )