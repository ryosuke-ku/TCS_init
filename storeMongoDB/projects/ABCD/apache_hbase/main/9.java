  public static void encrypt(OutputStream out, InputStream in, Context context,
      byte[] iv) throws IOException {
    Encryptor e = context.getCipher().getEncryptor();
    e.setKey(context.getKey());
    e.setIv(iv); // can be null
    e.reset();
    encrypt(out, in, e);
  }
