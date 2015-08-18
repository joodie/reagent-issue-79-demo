(ns reagent-p79-demo.prod
  (:require [reagent-p79-demo.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
