(ns user
  (:require (me.raynes [conch :as sh])
            (eu.cassiel.clj-images [core :as c])))

(binding
    [c/*PATH* "/opt/local/bin/"]
  (c/dimensions "/Users/nick/Dropbox/documents/portfolio/uncropped/aito.jpg"))
