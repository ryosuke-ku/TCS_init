    public void add(int value) {
        int index = ints.binarysearch(value);

        if (index < 0) {
            ints.insert(-(index + 1), value);
        }
    }
