(ns user-auth.core)

(defn is-bearer-algo? [algo]
  (= algo "Bearer"))

(defn valid-user?
  [user_tokens request_headers]
  (let [
      user-name (get request_headers "User-Name")
      authorisation-str (get request_headers "Authorisation" "")
      [algo-type user-token] (clojure.string/split authorisation-str #" ")]
    (and (= user-token (get user_tokens user-name)) (is-bearer-algo? algo-type))))
