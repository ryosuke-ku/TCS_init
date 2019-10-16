  public static CipherProvider getCipherProvider(Configuration conf) {
    String providerClassName = conf.get(HConstants.CRYPTO_CIPHERPROVIDER_CONF_KEY,
      DefaultCipherProvider.class.getName());
    try {
      CipherProvider provider = (CipherProvider)
        ReflectionUtils.newInstance(getClassLoaderForClass(CipherProvider.class)
          .loadClass(providerClassName),
        conf);
      return provider;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
