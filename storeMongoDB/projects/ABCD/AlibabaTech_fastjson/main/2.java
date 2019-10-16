    public void write(String text) {
        if (text == null) {
            writeNull();
            return;
        }

        int length = text.length();
        int newcount = count + length;
        if (newcount > buf.length) {
            expandCapacity(newcount);
        }
        text.getChars(0, length, buf, count);
        count = newcount;
        return;

    }
