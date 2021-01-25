(ns test.reflexiveClosure (:gen-class))
(require '[test.relationUtils :as relationUtils])

(defn reflexiveRelations
  "Given relation, returns the reflexive relations for it's mapper and mapped"
  [relation]
  (def mapper (relationUtils/getMapper relation))
  (def mapped (relationUtils/getMapped relation))
  (hash-set relation 
    ( hash-map mapper mapper ) 
    ( hash-map mapped mapped )
  )
)

(defn _reflexiveClosure
  "Recursive operation of reflexive closure"
  [relation relationship]
  (if (empty? relationship)
    (reflexiveRelations relation)
    (into 
      (_reflexiveClosure (first relationship) (drop 1 relationship) ) 
      (reflexiveRelations relation) 
    )
  )
)

(defn reflexiveClosure
    "Adds reflexive property to a relation"
    [relationship]  
      (if (empty? relationship)
        relationship
        (_reflexiveClosure (first relationship) (drop 1 relationship))
      )
)