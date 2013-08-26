(ns user
  (:require (me.raynes [conch :as sh])
            (eu.cassiel.clj-imagemagick [core :as im])))

(binding
    [im/*PATH* "/opt/local/bin/"]
  (im/dimensions "/Users/nick/Dropbox/documents/portfolio/uncropped/aito.jpg"))
