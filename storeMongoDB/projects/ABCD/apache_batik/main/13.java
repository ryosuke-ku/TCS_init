    public boolean retainAll(RectListManager rlm) {
        int x, xChange = 0;
        boolean ret = false;

        for (int j=0, i=0; j<size; j++) {
            i=xChange;
            while (rlm.rects[i].x < rects[j].x) {
                i++;
                if (i == rlm.size) break;
            }
            if (i == rlm.size) {
                ret = true;
                // No more rects will match anything from rlm
                // so remove them from this RLM.
                for (int k=j; k<size; k++)
                    rects[k] = null;
                size = j;
                break;
            }

            xChange = i;
            x = rlm.rects[i].x;
            while (true) {
                if (rects[j].equals(rlm.rects[i])) break;
                i++;
                if ((i == rlm.size) ||
                    (x != rlm.rects[i].x)) {
                    // Out of zone or rects
                    rects[j] = null;
                    ret = true;
                    break;
                }
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
