(ns alphabet-cipher.coder)

(defn lowerAscii [letter]
  "Ascii code of lowercase version of the letter"
  (int (Character/toLowerCase letter)))

(def a (lowerAscii \A))

(defn char->idx [letter]
  "Gives the 'index' of a letter A being O"
  (- (lowerAscii letter) a))

(defn idx->char [letter]
  "Reverts the index to a lower letter."
  (char (+ a (lowerAscii letter))))

(defn str->seq [kwd]
  "Turns a string into an lazy infinite sequence of chars"
  (concat (seq kwd) (lazy-seq (str->seq kwd))))

(defn apply-to-idx [f k l]
  (idx->char (mod (f (char->idx k)
                     (char->idx l))
                  26)))

(defn apply-to-str [f str1 str2]
  (apply str (map (fn [a b] (apply-to-idx f a b)) str1 str2)))

(defn encode [kwd message]
  "encodeme"
  (apply-to-str + (str->seq kwd) message))


(defn decode [kwd message]
  "decodeme"
  (apply-to-str - message (str->seq kwd)))

(defn simplify [secret]
  (loop [n 1]
    (if (= (subs secret 0 n) (subs secret n (* 2 n)))
      (subs secret 0 n)
      (recur (inc n)))))

(defn decipher [cipher message]
  "decypherme"
  (simplify (apply-to-str - cipher message)))


