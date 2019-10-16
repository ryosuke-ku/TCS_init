  public String trimTrailingFrom(CharSequence sequence) {
    int len = sequence.length();
    int last;

    for (last = len - 1; last >= 0; last--) {
      if (!matches(sequence.charAt(last))) {
        break;
      }
    }

    return sequence.subSequence(0, last + 1).toString();
  }
