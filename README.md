`-*- word-wrap: t; -*-`

# clj-imagemagick

Some Clojure to manipulate images with [ImageMagick](http://www.imagemagick.org), called via [Conch](https://github.com/Raynes/conch).

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

## License

Copyright © 2013 Nick Rothwell.

Distributed under the Eclipse Public License, the same as Clojure.
