(ns user
  (:require (me.raynes [conch :as sh])
            (eu.cassiel.clj-imagemagick [core :as im])))

(binding
    [im/*PATH* "/opt/local/bin/"]
  (im/dimensions "/Users/nick/Dropbox/documents/portfolio/uncropped/aito.jpg"))

(binding
    [im/*PATH* "/opt/local/bin/"]
  (let [root "/Users/nick/Dropbox/documents/portfolio/"]
    (im/convert (str root "uncropped/aito.jpg")
                (str root "aito.jpg")
                "-extent" "1890x1050"
                "-background" "black"
                "-gravity" "center")))

;; Pad to aspect ratio

(binding [im/*PATH* "/opt/local/bin/"]
  (let [root "/Users/nick/Dropbox/documents/portfolio/"
        ratio 1
        {:keys [width height]} (im/dimensions (str root "uncropped/aito.jpg"))
        width' (max width (* height ratio))
        height' (max height (/ width ratio))]
    {:original {:w width
                :h height}
     :final {:w (int width')
             :h (int height')}}))

(defn foo [a & {:keys [b c]}]
  {:a a :b b :c c})

(foo 1 :b "B" :c "C")

(binding [im/*PATH* "/opt/local/bin/"]
  (let [root "/Users/nick/Dropbox/documents/portfolio/"]
    (im/crop-aspect (str root "uncropped/aito.jpg")
                    (str root "aito.jpg")
                    :aspect 1
                    :background "black"
                    :gravity :northwest)))

(binding [im/*PATH* "/opt/local/bin/"]
  (let [root "/Users/nick/Dropbox/documents/portfolio/"
        {:keys [width height]} (im/dimensions (str root "uncropped/aito.jpg"))]
    (im/convert (str root "uncropped/aito.jpg")
                (str root "aito.jpg")
                "-extent" (format "%dx%d" (+ width 20) (+ height 20))
                "-gravity" "center"
                "-background" "#303030")))

(binding [im/*PATH* "/opt/local/bin/"]
  (let [root "/Users/nick/Dropbox/documents/portfolio/"]
    (im/border (str root "uncropped/aito.jpg")
               (str root "aito.jpg")
               :border-width 100
               :border-height 50
               :background "#808080")))
