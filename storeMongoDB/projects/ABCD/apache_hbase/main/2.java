    public Context setKey(byte[] key) {
      super.setKey(new SecretKeySpec(key, getCipher().getName()));
      return this;
    }
