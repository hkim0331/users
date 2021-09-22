(ns users.db
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

;; protocol?

(defn create-user! [params]
  (sql/insert! ds :users params builder))

(defn find-user [login]
  (if-let [ret (sql/query ds ["select * from users where login=?" login] builder)]
   (first ret)
   "can not find"))

(defn update-user! [login params]
 (sql/update! ds :users params {:login login} builder))

(defn delete-user! [login]
 (sql/delete! ds :users {:login login} builder))

(defn list-users []
 (sql/query ds ["select * from users"] builder))