(ns reagent-p79-demo.core
  (:require-macros [cljs.core.async.macros :refer [go go-loop]])
  (:require [cljs.core.async :as async :refer [put! chan <!]]
            [reagent.core :as reagent :refer [atom]]))

(defonce state (atom {:field-value "initial value"}))

(defonce events (chan 10))

(defonce event-loop
  (go-loop []
    (when-let [e (<! events)]
      (swap! state assoc :field-value (:value e))
      (recur))))

(defn demo
  []
  [:div.demo
   [:h3 "Put the cursor somewhere in the field below and enter stuff"]
   [:input {:value (:field-value @state)
            :on-change #(put! events {:value (-> % .-target .-value)})}]])

(defn mount-root []
  (reagent/render [demo] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
