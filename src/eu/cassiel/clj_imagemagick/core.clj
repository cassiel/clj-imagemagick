(ns eu.cassiel.clj-imagemagick.core
  (:require (me.raynes [conch :as sh])))

(def ^:dynamic *PATH* "")

(defn identify [format file]
  (sh/let-programs [id (str *PATH* "identify")]
                   (clojure.string/split (id "-format" format file) #"\s+")))

(defn dimensions [file]
  (let [[w h] (identify "%w %h" file)]
    {:width (Integer/parseInt w)
     :height (Integer/parseInt h)}))
