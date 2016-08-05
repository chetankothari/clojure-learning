(ns user-auth.core)

(defn is-bearer-algo? [algo]
  (= algo "Bearer"))

(defn auth-attrs [request_headers]
  (clojure.string/split
    (get request_headers "Authorisation" "") #" "))

(defn valid-user?
  [user_tokens request_headers]
  (let [user-name (get request_headers "User-Name")
        [algo-type user-token] (auth-attrs request_headers)]
    (and (= user-token (get user_tokens user-name)) (is-bearer-algo? algo-type))))
