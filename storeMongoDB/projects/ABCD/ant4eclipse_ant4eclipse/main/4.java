  public String asClassFileName() {
    String fileName = getQualifiedClassName().replace('.', '/');
    return fileName + ".class";
  }
