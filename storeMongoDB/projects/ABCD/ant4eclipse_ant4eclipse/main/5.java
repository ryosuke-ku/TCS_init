  public String asSourceFileName() {
    String fileName = getQualifiedClassName().replace('.', '/');
    return fileName + ".java";
  }
