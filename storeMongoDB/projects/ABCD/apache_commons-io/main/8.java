    public long skip(long n) throws IOException {
        int skipped = 0;
        while ((n > skipped) && (readFirstBytes() >= 0)) {
            skipped++;
        }
        return in.skip(n - skipped) + skipped;
    }
