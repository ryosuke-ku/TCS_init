    public void writeIntArray(int[] array) {
        int[] sizeArray = new int[array.length];
        int totalSize = 2;
        for (int i = 0; i < array.length; ++i) {
            if (i != 0) {
                totalSize++;
            }
            int val = array[i];
            int size;
            if (val == Integer.MIN_VALUE) {
                size = "-2147483648".length();
            } else {
                size = (val < 0) ? IOUtils.stringSize(-val) + 1 : IOUtils.stringSize(val);
            }
            sizeArray[i] = size;
            totalSize += size;
        }

        int newcount = count + totalSize;
        if (newcount > buf.length) {
            expandCapacity(newcount);
        }

        buf[count] = '[';

        int currentSize = count + 1;
        for (int i = 0; i < array.length; ++i) {
            if (i != 0) {
                buf[currentSize++] = ',';
            }

            int val = array[i];
            if (val == Integer.MIN_VALUE) {
                System.arraycopy("-2147483648".toCharArray(), 0, buf, currentSize, sizeArray[i]);
                currentSize += sizeArray[i];
            } else {
                currentSize += sizeArray[i];
                IOUtils.getChars(val, currentSize, buf);
            }
        }
        buf[currentSize] = ']';

        count = newcount;
    }
