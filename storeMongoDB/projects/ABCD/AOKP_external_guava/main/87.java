  public String trimLeadingFrom(CharSequence sequence) {
    int len = sequence.length();
    int first;

    for (first = 0; first < len; first++) {
      if (!matches(sequence.charAt(first))) {
        break;
      }
    }

    return sequence.subSequence(first, len).toString();
  }
