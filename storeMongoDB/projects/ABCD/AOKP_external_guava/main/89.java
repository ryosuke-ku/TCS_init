  public String collapseFrom(CharSequence sequence, char replacement) {
    int first = indexIn(sequence);
    if (first == -1) {
      return sequence.toString();
    }

    // TODO(kevinb): see if this implementation can be made faster
    StringBuilder builder = new StringBuilder(sequence.length())
        .append(sequence.subSequence(0, first))
        .append(replacement);
    boolean in = true;
    for (int i = first + 1; i < sequence.length(); i++) {
      char c = sequence.charAt(i);
      if (apply(c)) {
        if (!in) {
          builder.append(replacement);
          in = true;
        }
      } else {
        builder.append(c);
        in = false;
      }
    }
    return builder.toString();
  }
