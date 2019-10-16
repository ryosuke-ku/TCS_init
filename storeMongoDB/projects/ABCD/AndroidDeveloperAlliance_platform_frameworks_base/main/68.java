        public String get(int index) {
            if (index >= size) {
                throw new IndexOutOfBoundsException();
            }

            return segments[index];
        }
