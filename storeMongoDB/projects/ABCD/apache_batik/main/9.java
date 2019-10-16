        public void remove() {
            if (!removeOk)
                throw new IllegalStateException
                    ("remove can only be called directly after next/previous");

            if (forward) idx--;
            if (idx != size-1)
                System.arraycopy(rects, idx+1, rects, idx, size-(idx+1));
            size--;
            rects[size] = null;
            removeOk = false;
        }
