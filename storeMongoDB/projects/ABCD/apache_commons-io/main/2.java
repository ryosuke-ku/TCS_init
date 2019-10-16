    public int read(final byte[] b, final int off, final int len) throws IOException {
        if (max>=0 && pos>=max) {
            return EOF;
        }
        final long maxRead = max>=0 ? Math.min(len, max-pos) : len;
        final int bytesRead = in.read(b, off, (int)maxRead);

        if (bytesRead==EOF) {
            return EOF;
        }

        pos+=bytesRead;
        return bytesRead;
    }
