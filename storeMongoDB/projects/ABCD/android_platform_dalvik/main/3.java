    public void merge(IntSet other) {
        if (other instanceof ListIntSet) {
            ListIntSet o = (ListIntSet) other;
            int szThis = ints.size();
            int szOther = o.ints.size();

            int i = 0;
            int j = 0;

            while (j < szOther && i < szThis) {
                while (j < szOther && o.ints.get(j) < ints.get(i)) {
                    add(o.ints.get(j++));
                }
                if (j == szOther) {
                    break;
                }
                while (i < szThis && o.ints.get(j) >= ints.get(i)) {
                    i++;
                }
            }

            while (j < szOther) {
                add(o.ints.get(j++));
            }

            ints.sort();
        } else if (other instanceof BitIntSet) {
            BitIntSet o = (BitIntSet) other;

            for (int i = 0; i >= 0; i = Bits.findFirst(o.bits, i + 1)) {
                ints.add(i);
            }
            ints.sort();
        } else {
            IntIterator iter = other.iterator();
            while (iter.hasNext()) {
                add(iter.next());
            }
        }
    }
