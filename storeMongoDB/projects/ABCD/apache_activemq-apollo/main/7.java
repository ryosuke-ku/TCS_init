    public TreeEntry<K, V> lowerEntry(K key) {
        TreeEntry<K, V> n = root;
        TreeEntry<K, V> l = null;
        while (n != null) {
            int c = compare(key, n.key);
            if (c <= 0) {
                n = n.left;
            } else {
                //Update the low node:
                if (l == null || compare(n.key, l.key) > 0) {
                    l = n;
                }
                //Now need to scan the right tree to see if there is
                //a higher low value to consider:
                if (n.right == null) {
                    break;
                }
                n = n.right;
            }
        }
        return l;
    }
