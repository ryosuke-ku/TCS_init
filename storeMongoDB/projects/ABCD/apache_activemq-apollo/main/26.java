        public Entry<K, V> next() {
            getNext();
            if (last == null) {
                return null;
            } else {
                return last;
            }
        }
