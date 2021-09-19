(ns users.boundary
 (:require
  [environ.core :refer [env]]
  [next.jdbc :refer [get-connection]]
  [next.jdbc.result-set :as rs]
  [next.jdbc.sql :as sql]))

(def ^:private db
 {:dbtype "postgres"
  :dbname "users"
  :user (env :users-user)
  :password (env :users-password)})

(def ^:private ds (get-connection db))

(def ^:private builder {:builder-fn rs/as-unqualified-lower-maps})

(defn create-user! [params]
 (sql/insert! ds :users params))

(defn find-user [login]
  (if-let [ret (sql/query ds ["select * from users where login=?" login])]
   (first ret)
   "can not find"))

(defn update-user! [login params]
 (sql/update! ds :users params {:login login}))

(defn delete-user! [login]
 (sql/execute! ds ["delete from users where login=?" login]))

(defn list-users []
 (sql/query ds ["select * from users"]))