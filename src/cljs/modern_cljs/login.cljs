(ns modern-cljs.login
  (:use [domina :only [by-id value]]))

;; define the function to be attached to the form submission event
(defn validate-form []
  ;; get email and password element from their IDs in the HTML form
  (let [email (by-id "email")
        password (by-id "password")]
    (if (and (> (count (value email)) 0)
             (> (count (value password)) 0))
      true
      (do (js/alert "Please complete the form!")
          false))))


;; define the function to attach validate-form to onSubmit event of the form
(defn init []
  ;; verify that js/document exists and has a getElementById property)
  (if (and js/document (.-getElementById js/document))
    ;; get loginform by element ID and set its onsubmit property to our validate-form function
    (let [login-form (.getElementById js/document "loginForm")]
      (set! (.-onsubmit login-form) validate-form))))

;; initialize the HTML page in unobtrusive way
(set! (.-onload js/window) init) 
