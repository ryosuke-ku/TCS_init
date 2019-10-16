  public static final void isDirectory(String param, File file) {
    Assure.exists(param, file);
    if (!file.isDirectory()) {
      throw new Ant4EclipseException(CoreExceptionCode.PRECONDITION_VIOLATION, String.format(
          MSG_RESOURCEISNOTADIRECTORY, file.getAbsolutePath()));
    }
  }
