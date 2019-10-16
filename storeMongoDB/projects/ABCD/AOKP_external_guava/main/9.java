  public static Splitter fixedLength(final int length) {
    checkArgument(length > 0, "The length may not be less than 1");

    return new Splitter(new Strategy() {
      @Override public SplittingIterator iterator(
          final Splitter splitter, CharSequence toSplit) {
        return new SplittingIterator(splitter, toSplit) {
          @Override public int separatorStart(int start) {
            int nextChunkStart = start + length;
            return (nextChunkStart < toSplit.length() ? nextChunkStart : -1);
          }

          @Override public int separatorEnd(int separatorPosition) {
            return separatorPosition;
          }
        };
      }
    });
  }
