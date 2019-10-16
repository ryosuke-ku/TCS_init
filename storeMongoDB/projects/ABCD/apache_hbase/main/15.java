  public static KeyProvider getKeyProvider(Configuration conf) {
    String providerClassName = conf.get(HConstants.CRYPTO_KEYPROVIDER_CONF_KEY,
      KeyStoreKeyProvider.class.getName());
    String providerParameters = conf.get(HConstants.CRYPTO_KEYPROVIDER_PARAMETERS_KEY, "");
    try {
      Pair<String,String> providerCacheKey = new Pair<String,String>(providerClassName,
        providerParameters);
      KeyProvider provider = keyProviderCache.get(providerCacheKey);
      if (provider != null) {
        return provider;
      }
      provider = (KeyProvider) ReflectionUtils.newInstance(
        getClassLoaderForClass(KeyProvider.class).loadClass(providerClassName),
        conf);
      provider.init(providerParameters);
      if (LOG.isDebugEnabled()) {
        LOG.debug("Installed " + providerClassName + " into key provider cache");
      }
      keyProviderCache.put(providerCacheKey, provider);
      return provider;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
