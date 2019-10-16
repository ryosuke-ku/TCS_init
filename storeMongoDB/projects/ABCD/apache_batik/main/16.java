    public void subtract(RectListManager rlm, int overhead, int lineOverhead) {
        Rectangle r, sr;
        int cost;
        int jMin=0;
        Rectangle [] splits = new Rectangle[4];

        for(int i=0; i<size; i++) {
            r = rects[i]; // Canidate rect...
            cost = (overhead                +
                    (r.height*lineOverhead) +
                    (r.height*r.width));
            for (int j=jMin; j<rlm.size; j++) {
                sr = rlm.rects[j]; // subtraction rect.

                // Check if the canidate rect starts after
                // the end of this rect in 'x' if so
                // go to the next one.
                if (sr.x+sr.width < r.x) {
                    // If this was jMin then increment jMin (no
                    // future canidate rect will intersect this rect).
                    if (j == jMin) jMin++;
                    continue;
                }

                // Check if the rest of the rects from rlm are past
                // the end of the canidate rect.  If so we are
                // done with this canidate rect.
                if (sr.x > r.x+r.width)
                    break;

                // If they don't insersect then go to next sub rect.
                if (!r.intersects(sr))
                    continue;

                // Now we know they intersect one another lets
                // figure out how...

                splitRect(r, sr, splits);

                int splitCost=0;
                Rectangle tmpR;
                for (int k=0; k<4; k++) {
                    tmpR = splits[k];
                    if (tmpR != null)
                        splitCost += (overhead                   +
                                      (tmpR.height*lineOverhead) +
                                      (tmpR.height*tmpR.width));
                }

                if (splitCost >= cost)
                    // This isn't ideal as depending on the order
                    // Stuff is done in we might later kill some of
                    // these rectangles (hence lowering the cost).
                    // For this reason it is probably best of the
                    // subtract list has been merged as this will help
                    // reduce the instances where this will happen.
                    continue;

                // Collapse null entries in first three elements
                // split 0, 1, 2 (entries that share a common 'x').
                int l = 0;
                for (int k=0; k<3; k++) {
                    if (splits[k] != null)
                        splits[l++] = splits[k];
                }

                // Fully covered (or only split 3 survived which we
                // will visit later) this canidate rect goes away.
                if (l==0) {
                    rects[i].width = 0;
                    // Insert the third split (if any) at the
                    // proper place in rects list.
                    if (splits[3] != null)
                        add(splits[3], i, size-1);
                    break;
                }

                // Otherwise replace the canidate with the top of
                // the split, since it only shrunk it didn't grow,
                // we know that the previous subtract rects don't
                // intersect it.
                r        = splits[0];
                rects[i] = r;
                cost = (overhead                +
                        (r.height*lineOverhead) +
                        (r.height*r.width));

                // Add the remainder of the rects that
                // share 'r.x' (if any).  Possible
                // are split 1, and split 2.
                if (l > 1)
                    insertRects(splits, 1, i+1, l-1);

                // Insert the third split (if any) at the
                // proper place in rects list.
                if (splits[3] != null)
                    add(splits[3], i+l, size-1);
            }
        }

        // Now we will go through collapsing the nulled entries.
        int j=0, i=0;
        while (i<size) {
            if (rects[i].width == 0)
                rects[i] = null;
            else
                rects[j++] = rects[i];
            i++;
        }
        size = j;
        bounds = null;
    }
