  public static final ClassName fromQualifiedClassName(String qualifiedClassName) {
    Assure.nonEmpty("qualifiedClassName", qualifiedClassName);
    return new ClassName(qualifiedClassName);
  }
