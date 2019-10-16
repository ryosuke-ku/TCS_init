    public static void swap(final short[] array,  int offset1, int offset2, int len) {
        if (array == null || array.length == 0 || offset1 >= array.length || offset2 >= array.length) {
            return;
        }
        if (offset1 < 0) {
            offset1 = 0;
        }
        if (offset2 < 0) {
            offset2 = 0;
        }
        if (offset1 == offset2) {
            return;
        }
        len = Math.min(Math.min(len, array.length - offset1), array.length - offset2);
        for (int i = 0; i < len; i++, offset1++, offset2++) {
            final short aux = array[offset1];
            array[offset1] = array[offset2];
            array[offset2] = aux;
        }
    }
