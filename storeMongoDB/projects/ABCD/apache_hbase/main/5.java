  public static Cipher getCipher(Configuration conf, String name) {
    return getCipherProvider(conf).getCipher(name);
  }
