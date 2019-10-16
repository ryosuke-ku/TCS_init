        public void set(Object o) {
            Rectangle r = (Rectangle)o;

            if (!removeOk)
                throw new IllegalStateException
                    ("set can only be called directly after next/previous");

            if (forward) idx--;

            if (idx+1<size) {
                if (rects[idx+1].x < r.x)
                    throw new UnsupportedOperationException
                        ("RectListManager entries must be sorted");
            }
            if (idx>=0) {
                if (rects[idx-1].x > r.x)
                    throw new UnsupportedOperationException
                        ("RectListManager entries must be sorted");
            }

            rects[idx] = r;
            removeOk = false;
        }
