`-*- word-wrap: t; -*-`

# clj-imagemagick

Some Clojure to manipulate images with [ImageMagick](http://www.imagemagick.org), called via [Conch](https://github.com/Raynes/conch). (Some of this code probably duplicates functionality in the more esoteric command line options, but I wanted to provide clear interfaces.)

## Usage

```clojure
(ns user
  (:require (eu.cassiel.clj-imagemagick [core :as im])))
```

Determine the dimensions of an image:

```clojure
(binding
    [im/*PATH* "/opt/local/bin/"]
  (im/dimensions "/Users/nick/Dropbox/documents/portfolio/uncropped/aito.jpg"))
;; => {:width 1680, :height 1050}
```

Modify an image to a specific aspect ratio by extending (with `background` and `gravity` options) or cropping:

```clojure
(im/extend-aspect "in.jpg" "out.jpg"
                  :aspect 16/9
                  :gravity :center
                  :background "black")
```

(Also: `crop-aspect`. See [the ImageMagick documentation](http://www.imagemagick.org/script/command-line-options.php#gravity) for gravity options.)

Adding a symmetric border:

```clojure
(im/border "in.jpg" "out.jpg"
           :border-width 100
           :border-height 50
           :background "#808080")
```

## License

Copyright © 2013 Nick Rothwell.

Distributed under the Eclipse Public License, the same as Clojure.
