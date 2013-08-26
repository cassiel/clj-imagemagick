(ns eu.cassiel.clj-imagemagick.core
  (:require (me.raynes [conch :as sh])))

(def ^:dynamic *PATH* "")

(defn identify
  "Run `identity` with the specified output format, return space-separated output."
  [format file]
  (sh/let-programs [id (str *PATH* "identify")]
                   (clojure.string/split (id "-format" format file) #"\s+")))

(defn dimensions
  "Determine pixel dimensions of a file."
  [file]
  (let [[w h] (identify "%w %h" file)]
    {:width (Integer/parseInt w)
     :height (Integer/parseInt h)}))

(defn convert
  "Convert in-file to out-file with specified options"
  [in-file out-file & args]
  (sh/let-programs [cv (str *PATH* "convert")]
                   (apply cv (concat args [in-file out-file]))))

(defn aspect-resize
  "Resize via `extent` to an aspect ratio, with optional background and gravity."
  [min-max in-file out-file & {:keys [aspect background gravity]}]
  (let [aspect (or aspect 1)
        background (or background "black")
        gravity (name (or gravity :center))
        {:keys [width height]} (dimensions in-file)
        width' (min-max width (* height aspect))
        height' (min-max height (/ width aspect))]
    (convert in-file out-file
             "-extent" (format "%dx%d" (int width') (int height'))
             "-background" background
             "-gravity" gravity)
    {:original {:w width
                :h height}
     :final {:w (int width')
             :h (int height')}}))

(def extend-aspect (partial aspect-resize max))

(def crop-aspect (partial aspect-resize min))

(defn border
  "Pad an image symmetrically with a coloured border"
  [in-file out-file & {:keys [border-width border-height background]}]
  (let [border-width (or border-width 0)
        border-height (or border-height 0)
        background (or background "black")
        {:keys [width height]} (dimensions in-file)]
    (convert in-file out-file
             "-extent" (format "%dx%d"
                               (+ width (* border-width 2))
                               (+ height (* border-height 2)))
             "-gravity" "center"
             "-background" background)))
