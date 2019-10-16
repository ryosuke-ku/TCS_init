  public static final void isFile(String param, File file) {
    Assure.exists(param, file);
    if (!file.isFile()) {
      throw new Ant4EclipseException(CoreExceptionCode.PRECONDITION_VIOLATION, String.format(
          MSG_RESOURCEISNOTAREGULARFILE, file.getAbsolutePath()));
    }
  }
