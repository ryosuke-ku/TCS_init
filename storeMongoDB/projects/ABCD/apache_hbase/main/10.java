  public static void decrypt(OutputStream out, InputStream in, int outLen,
      Context context, byte[] iv) throws IOException {
    Decryptor d = context.getCipher().getDecryptor();
    d.setKey(context.getKey());
    d.setIv(iv); // can be null
    decrypt(out, in, outLen, d);
  }
