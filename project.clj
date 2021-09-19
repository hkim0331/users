(defproject users "0.1.0"
  :description "clojure/reitit の練習"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[environ "1.2.0"]
                 [metosin/muuntaja "0.6.8"]
                 [metosin/reitit "0.5.15"]
                 [metosin/ring-http-response "0.9.3"]
                 [com.github.seancorfield/next.jdbc "1.2.709"]
                 [org.clojure/clojure "1.10.3"]
                 [org.postgresql/postgresql "42.2.23"]
                 [ring "1.9.4"]]
  :repl-options {:init-ns users.core}
  :main users.core)