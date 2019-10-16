    public static boolean isSorted(final boolean[] array) {
        if (array == null || array.length < 2) {
            return true;
        }

        boolean previous = array[0];
        final int n = array.length;
        for (int i = 1; i < n; i++) {
            final boolean current = array[i];
            if (BooleanUtils.compare(previous, current) > 0) {
                return false;
            }

            previous = current;
        }
        return true;
    }
