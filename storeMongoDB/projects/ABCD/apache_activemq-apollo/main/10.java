    public TreeEntry<K, V> ceilingEntry(K key) {
        TreeEntry<K, V> n = root;
        TreeEntry<K, V> h = null;
        while (n != null) {
            int c = compare(key, n.key);
            if (c == 0) {
                return n;
            }

            if (c > 0) {
                n = n.right;
            } else {
                //Update the high node:
                if (h == null || compare(n.key, h.key) < 0) {
                    h = n;
                }
                //Now need to scan the left tree to see if there is
                //a lower high value to consider:
                if (n.left == null) {
                    break;
                }
                n = n.left;
            }
        }
        return h;
    }
