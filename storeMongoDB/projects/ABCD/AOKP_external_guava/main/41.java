    @Override public String toString() {
      return Objects.toStringHelper(this)
          .add("pattern", pattern)
          .add("pattern.flags", Integer.toHexString(pattern.flags()))
          .toString();
    }
