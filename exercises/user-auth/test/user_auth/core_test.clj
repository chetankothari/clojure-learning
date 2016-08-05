(ns user-auth.core-test
  (:require [clojure.test :refer :all]
            [user-auth.core :refer :all]))

(deftest test-valid-user?
  (let [user_tokens {"User1" "Token1"
                     "User2" "Token2"}]
    (testing "returns false if User-Name header is not give"
      (is (not (valid-user? user_tokens {"User-Name" "User1"}))))

    (testing "returns false if Authorisation header is not give"
      (is (not (valid-user? user_tokens {"Authorisation" "Bearer Token1"}))))

    (testing "returns false if Authorisation header does not have Algorithm type in it"
      (is (not (valid-user? user_tokens {"User-Name" "User1"
                                         "Authorisation" "Token1"}))))

    (testing "returns false if Authorisation header is empty"
      (is (not (valid-user? user_tokens {"User-Name" "User1"
                                         "Authorisation" ""}))))

    (testing "returns false if non existent user is given"
      (is (not (valid-user? user_tokens {"User-Name" "User3"
                                         "Authorisation" "Bearer Token1"}))))

    (testing "returns true if user and token exists in user_tokens"
      (is (valid-user? user_tokens {"User-Name" "User1"
                                    "Authorisation" "Bearer Token1"})))))

(deftest test-is-bearer-algo?
  (testing "returns false if bearer-type is not Bearer"
      (is (not (is-bearer-algo? "Test-Type"))))

  (testing "returns true if bearer-type is Bearer"
      (is (is-bearer-algo? "Bearer"))))
