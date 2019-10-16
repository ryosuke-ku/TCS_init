  public static void testKeyProvider(final Configuration conf) throws IOException {
    String providerClassName = conf.get(HConstants.CRYPTO_KEYPROVIDER_CONF_KEY,
      KeyStoreKeyProvider.class.getName());
    Boolean result = keyProviderResults.get(providerClassName);
    if (result == null) {
      try {
        Encryption.getKeyProvider(conf);
        keyProviderResults.put(providerClassName, true);
      } catch (Exception e) { // most likely a RuntimeException
        keyProviderResults.put(providerClassName, false);
        throw new IOException("Key provider " + providerClassName + " failed test: " +
          e.getMessage(), e);
      }
    } else if (result.booleanValue() == false) {
      throw new IOException("Key provider " + providerClassName + " previously failed test");
    }
  }
