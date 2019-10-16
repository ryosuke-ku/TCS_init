    public void mergeRects(int overhead, int lineOverhead) {
        if (size == 0) return;
        Rectangle r, cr;
        int cost1, cost2, cost3;
        Rectangle []splits = new Rectangle[4];
        for (int j, i=0; i<size; i++) {
            r = rects[i];
            if (r == null) continue;
            cost1 = (overhead                 +
                     (r.height*lineOverhead) +
                     (r.height*r.width));
            do {
                int maxX = r.x+r.width+overhead/r.height;
                for (j=i+1; j<size; j++) {
                    cr = rects[j];
                    if ((cr == null) || (cr == r)) continue;
                    if (cr.x >= maxX) {
                        // No more merges can happen.
                        j = size;
                        break;
                    }
                    cost2 = (overhead                 +
                             (cr.height*lineOverhead) +
                             (cr.height*cr.width));

                    Rectangle mr = r.union(cr);
                    cost3 = (overhead                 +
                             (mr.height*lineOverhead) +
                             (mr.height*mr.width));
                    if (cost3 <= cost1+cost2) {
                        r = rects[i] = mr;
                        rects[j] = null;
                        cost1 = cost3;
                        j=-1;
                        break;
                    }

                    if (!r.intersects(cr)) continue;

                    splitRect(cr, r, splits);
                    int splitCost=0;
                    int l=0;
                    for (int k=0; k<4; k++) {
                        if (splits[k] != null) {
                            Rectangle sr = splits[k];
                            // Collapse null entries in first three
                            // (That share common 'x').
                            if (k<3) splits[l++] = sr;
                            splitCost += (overhead                 +
                                          (sr.height*lineOverhead) +
                                          (sr.height*sr.width));
                        }
                    }
                    if (splitCost >= cost2) continue;

                    // Insert the splits.
                    if (l == 0) {
                        // only third split may be left (no common 'x').
                        rects[j] = null;
                        if (splits[3] != null)
                            add(splits[3], j, size-1);
                        continue;
                    }

                    rects[j] = splits[0];
                    if (l > 1)
                        insertRects(splits, 1, j+1, l-1);
                    if (splits[3] != null)
                        add(splits[3], j, size-1);
                }

                // if we merged it with another rect then
                // we need to check all the rects up to i again,
                // against the merged rect.
            } while (j != size);
        }

        // Now we will go through collapsing the nulled entries.
        int j=0, i=0;
        float area=0;
        while (i<size) {
            if (rects[i] != null) {
                r = rects[i];
                rects[j++] = r;
                area += overhead + (r.height*lineOverhead) +
                    (r.height*r.width);
            }
            i++;
        }
        size = j;
        bounds=null;
        r = getBounds();
        if (r == null) return;
        if (overhead + (r.height*lineOverhead) + (r.height*r.width) < area) {
            rects[0] = r;
            size=1;
        }
    }
