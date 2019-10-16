        public Iterator<String> getIterator() {
            return new Iterators.Synchronized<String>(strings) {
                @Override
                protected void synchronizedRemove(int index) {
                    lock();
                    try {
                        strings.remove(index);
                    } finally {
                        unlock();
                    }
                }
            };
        }
