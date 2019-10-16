    public void writeLongAndChar(long i, char c) throws IOException {
        if (i == Long.MIN_VALUE) {
            write("-9223372036854775808");
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
