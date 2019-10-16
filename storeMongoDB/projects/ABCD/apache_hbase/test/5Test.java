  public static void testCipherProvider(final Configuration conf) throws IOException {
    String providerClassName = conf.get(HConstants.CRYPTO_CIPHERPROVIDER_CONF_KEY,
      DefaultCipherProvider.class.getName());
    Boolean result = cipherProviderResults.get(providerClassName);
    if (result == null) {
      try {
        Encryption.getCipherProvider(conf);
        cipherProviderResults.put(providerClassName, true);
      } catch (Exception e) { // most likely a RuntimeException
        cipherProviderResults.put(providerClassName, false);
        throw new IOException("Cipher provider " + providerClassName + " failed test: " +
          e.getMessage(), e);
      }
    } else if (result.booleanValue() == false) {
      throw new IOException("Cipher provider " + providerClassName + " previously failed test");
    }
  }
