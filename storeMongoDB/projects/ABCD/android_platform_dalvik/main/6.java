            public int next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return ints.get(idx++);
            }
