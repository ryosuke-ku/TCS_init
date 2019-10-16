        public void add(Object o) {
            Rectangle r = (Rectangle)o;
            if (idx<size) {
                if (rects[idx].x < r.x)
                    throw new UnsupportedOperationException
                        ("RectListManager entries must be sorted");
            }
            if (idx!=0) {
                if (rects[idx-1].x > r.x)
                    throw new UnsupportedOperationException
                        ("RectListManager entries must be sorted");
            }
            ensureCapacity(size+1);
            if (idx != size)
                System.arraycopy(rects, idx, rects, idx+1, size-idx);
            rects[idx] = r;
            idx++;
            removeOk = false;
        }
