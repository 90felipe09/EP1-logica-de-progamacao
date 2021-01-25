(ns test.core)
(require '[test.inputParser :as inputParser])
(require '[test.reflexiveClosure :as reflexiveClosure])
(require '[test.transitiveClosure :as transitiveClosure])
(require '[test.relationUtils :as relationUtils])


(defn -main
  "EP 1 - Calculador de fecho"
  [fileInput]
  (def relationship (inputParser/getRelationshipFromInput fileInput) )
  (println (transitiveClosure/trincas (relationUtils/getRelationshipElements relationship)))
  (println (str "Relação entrada:" relationship))
  (def reflexiveClosure (reflexiveClosure/reflexiveClosure relationship ))
  (println (str "Fecho reflexivo sobre a relação:" reflexiveClosure))
  (def transitiveClosure (transitiveClosure/transit reflexiveClosure ))
  (println (str "Fecho reflexivo e transitivo sobre a relação:" transitiveClosure))
)