  public static boolean isBasicType(String type) {
    boolean flag = type.length() == 1;
    if (flag) {
      char ch = type.charAt(0);
      flag = (ch == JVM_TYPE_INT // Integer
          || ch == JVM_TYPE_BOOLEAN // Boolean
          || ch == JVM_TYPE_BYTE // byte
          || ch == JVM_TYPE_CHAR // char
          || ch == JVM_TYPE_SHORT // short
          || ch == JVM_TYPE_FLOAT // float
          || ch == JVM_TYPE_LONG // long
      || ch == JVM_TYPE_DOUBLE);// double
    }
    return flag;
  }
