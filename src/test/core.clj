(ns test.core)
(require '[test.inputParser :as inputParser])
(require '[test.reflexiveClosure :as reflexiveClosure])


(defn -main
  "EP 1 - Calculador de fecho"
  [fileInput]
  (def relationship (inputParser/getRelationshipFromInput fileInput) )
  (println "Relação entrada:" relationship)
  (def reflexiveClosure (reflexiveClosure/reflexiveClosure relationship ))
  (println "Fecho reflexivo sobre a relação:" reflexiveClosure)
)