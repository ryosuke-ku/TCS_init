    public synchronized void mark(final int readlimit) {
        markFbIndex = fbIndex;
        markedAtStart = firstBytes == null;
        in.mark(readlimit);
    }
