(ns users.core
  (:require
   [muuntaja.middleware :as muuntaja]
   [reitit.ring :as reitit]
   [ring.adapter.jetty :as jetty]
   [ring.middleware.reload :refer [wrap-reload]]
   [ring.util.http-response :as response]
   [users.boundary :refer [create-user! update-user! find-user delete-user! list-users]])
 (:gen-class))


(def routes
  [["/users"
    {:get
     (fn [_] (response/ok (list-users)))
     :post
     (fn [{params :body-params}]
      (response/ok (create-user! params)))
     :put
     (fn [{params :body-params}]
      (response/ok (update-user! (params :login) params)))}]
   ["/users/:login"
    {:get
     (fn [{{:keys [login]} :path-params}]
       (response/ok (find-user login)))
     :delete
     (fn [{{:keys [login]} :path-params}]
       (response/ok (delete-user! login)))}]])

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