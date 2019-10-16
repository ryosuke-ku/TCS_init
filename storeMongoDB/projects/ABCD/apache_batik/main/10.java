    public boolean removeAll(RectListManager rlm) {
        int x, xChange = 0;
        boolean ret = false;
        for (int j=0, i=0; j<rlm.size; j++) {
            i=xChange;
            while ((rects[i] == null) ||
                   (rects[i].x < rlm.rects[j].x)) {
                i++;
                if (i == size) break;
            }

            if (i == size) break;

            xChange = i;
            x = rects[i].x;
            while (true) {
                if (rects[i] == null) {
                    i++;
                    if (i == size) break; // out of rects
                    continue;
                }
                if (rlm.rects[j].equals(rects[i])) {
                    rects[i] = null;
                    ret = true;
                }
                i++;
                if (i == size)       break; // out of rects
                if (x != rects[i].x) break; // out of the zone.
            }
        }

        // Now we will go through collapsing the nulled entries.
        if (ret) {
            int j=0, i=0;
            while (i<size) {
                if (rects[i] != null)
                    rects[j++] = rects[i];
                i++;
            }
            size = j;
            bounds = null;
        }
        return ret;
    }
