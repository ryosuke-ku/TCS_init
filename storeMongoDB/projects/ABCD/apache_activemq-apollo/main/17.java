    public V put(final K key, final V value) {

        if (root == null) {
            // map is empty
            root = new TreeEntry<K, V>(key, value, null, this);
            count++;
            return null;
        }
        TreeEntry<K, V> n = root;

        // add new mapping
        while (true) {
            int c = compare(key, n.key);

            if (c == 0) {
                V old = n.value;
                n.value = value;
                return old;
            } else if (c < 0) {
                if (n.left != null) {
                    n = n.left;
                } else {
                    n.left = new TreeEntry<K, V>(key, value, n, this);
                    count++;
                    doRedBlackInsert(n.left);
                    return null;
                }
            } else { // c > 0
                if (n.right != null) {
                    n = n.right;
                } else {
                    n.right = new TreeEntry<K, V>(key, value, n, this);
                    count++;
                    doRedBlackInsert(n.right);
                    return null;
                }
            }
        }
    }
