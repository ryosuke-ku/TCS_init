    public boolean containsAll(RectListManager rlm) {
        int x, xChange = 0;
        for (int j=0, i=0; j<rlm.size; j++) {
            i=xChange;
            while(rects[i].x < rlm.rects[j].x) {
                i++;
                if (i == size) return false;
            }
            xChange = i;
            x = rects[i].x;
            while (!rlm.rects[j].equals(rects[i])) {
                i++;
                if (i == size) return false; // out of rects
                if (x != rects[i].x)
                    return false; // out of the zone.
            }
        }
        return true;
    }
