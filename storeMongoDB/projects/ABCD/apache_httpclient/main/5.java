        public T next() {
            if (next != null) {
                final T result = next.get();
                fetchNext();
                return result;
            } else {
                throw new NoSuchElementException();
            }
        }
