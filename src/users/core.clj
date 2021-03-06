(ns users.core
  (:require
   [buddy.hashers :as hashers]
   ;;[clojure.pprint :refer [pprint]]
   [muuntaja.middleware :as muuntaja]
   [reitit.ring :as reitit]
   [ring.adapter.jetty :as jetty]
   [ring.middleware.reload :refer [wrap-reload]]
   [ring.middleware.params :refer [wrap-params]]
   [ring.middleware.keyword-params :refer [wrap-keyword-params]]
   [ring.util.http-response :as response]
   [users.db :as db]
   [taoensso.timbre :as timbre :refer [debug]])
 (:gen-class

        ;;  (debug "body-params" params)
        ;;  (debug "params" (:params req))))
        ;;  (response/ok
        ;;   (db/create-user! (update params :password hashers/derive)))))
     :put
     (fn [{params :body-params}]
       (response/ok
        (db/update-user! (params :login)
                         (update params :password hashers/derive))))
   ["/users/:login"
    {:get
     (fn [{{:keys [login]} :path-params}]
       (response/ok (db/find-user login)))
     :delete
     (fn [{{:keys [login]} :path-params}]
       (response/ok (db/delete-user! login)))}]))

(def handler
  (reitit/ring-handler
   (reitit/router routes)
   (reitit/create-resource-handler {:path "/"})
   (reitit/create-default-handler
    {:not-found
     (constantly (response/not-found "404 - Page not found"))
     :method-not-allowed
     (constantly (response/method-not-allowed "405 - Not allowed"))
     :not-acceptable
     (constantly (response/not-acceptable "406 - Not acceptable"))})))

(defn -main []
  (jetty/run-jetty
   (-> #'handler
       muuntaja/wrap-format
       wrap-reload)
   {:port 3000
    :join? false}))
