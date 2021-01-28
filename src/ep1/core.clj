(ns ep1.core)
(require '[ep1.inputParser :as inputParser])
(require '[ep1.reflexiveClosure :as reflexiveClosure])
(require '[ep1.transitiveClosure :as transitiveClosure])
(require '[ep1.relationUtils :as relationUtils])


(defn -main
  "EP 1 - Calculador de fecho"
  [fileInput]
  (def relationship (inputParser/getRelationshipFromInput fileInput) )
  (println (transitiveClosure/triads (relationUtils/getRelationshipElements relationship)))
  (println (str "Relacao entrada: " relationship))
  (def reflexiveClosure (reflexiveClosure/reflexiveClosure relationship ))
  (println (str "Fecho reflexivo sobre a relacao: " reflexiveClosure))
  (def transitiveClosure (transitiveClosure/transitiveClosure reflexiveClosure ))
  (println (str "Fecho reflexivo e transitivo sobre a relacao: " transitiveClosure))
)