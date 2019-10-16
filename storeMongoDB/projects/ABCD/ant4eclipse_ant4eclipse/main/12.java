  public static final void inRange(int value, int from, int to) {
    if ((value < from) || (value > to)) {
      throw new Ant4EclipseException(CoreExceptionCode.PRECONDITION_VIOLATION, String.format(MSG_VALUEOUTOFRANGE,
          Integer.valueOf(value), Integer.valueOf(from), Integer.valueOf(to)));
    }
  }
