    public void writeInt(int i) {
        if (i == Integer.MIN_VALUE) {
            write("-2147483648");
            return;
        }

        int size = (i < 0) ? IOUtils.stringSize(-i) + 1 : IOUtils.stringSize(i);

        int newcount = count + size;
        if (newcount > buf.length) {
            expandCapacity(newcount);
        }

        IOUtils.getChars(i, newcount, buf);

        count = newcount;
    }
