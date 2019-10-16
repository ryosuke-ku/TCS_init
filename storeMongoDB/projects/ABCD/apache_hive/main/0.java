  static JavaDataModel getModelForSystem() {
    String props = null;
    try {
      props = System.getProperty("sun.arch.data.model");
    } catch (Exception e) {
      LOG.warn("Failed to determine java data model, defaulting to 64", e);
    }
    if ("32".equals(props)) {
      return JAVA32;
    }
    // TODO: separate model is needed for compressedOops, which can be guessed from memory size.
    return JAVA64;
  }
