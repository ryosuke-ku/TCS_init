        public Object next() {
            if (idx >= size)
                throw new NoSuchElementException("No Next Element");
            forward = true;
            removeOk = true;
            return rects[idx++];
        }
