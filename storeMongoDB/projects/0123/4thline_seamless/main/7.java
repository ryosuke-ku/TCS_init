        public E next() {
            removedCurrent = false;
            nextIndex++;
            return wrapped.next();
        }
