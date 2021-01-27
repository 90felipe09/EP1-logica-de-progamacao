(ns ep1.inputParser (:gen-class))
(require '[ep1.relationUtils :as relationUtils])

(defn splitCSV 
  "Receive string in csv format and returns 2-uples for each row"
  [csv] 
  (map #(clojure.string/split % #",") 
    (clojure.string/split csv #"\n")
  )
)

(defn parseRowsIntoRelation
  "Receives 2-uples and convert each in a map"
  [rows]
  (map relationUtils/createRelationshipElement rows)
)

(defn getRelationshipFromInput
  "given a filepath of a csv, returns a relationship set"
  [fileInput]
  (set (parseRowsIntoRelation (
      splitCSV (slurp fileInput)
      )
    )
  )
)