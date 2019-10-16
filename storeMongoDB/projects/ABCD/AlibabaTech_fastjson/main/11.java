    public void writeIntAndChar(int i, char c) {
        if (i == Integer.MIN_VALUE) {
            write("-2147483648");
            write(c);
            return;
        }

        int size = (i < 0) ? IOUtils.stringSize(-i) + 1 : IOUtils.stringSize(i);

        int newcount0 = count + size;
        int newcount1 = newcount0 + 1;

        if (newcount1 > buf.length) {
            expandCapacity(newcount1);
        }

        IOUtils.getChars(i, newcount0, buf);
        buf[newcount0] = c;

        count = newcount1;
    }
