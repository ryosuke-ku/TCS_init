  public void testGetModelForSystemWhenSetToUnknown() throws Exception {
    System.setProperty(DATA_MODEL_PROPERTY, "unknown");
    assertSame(JavaDataModel.JAVA64, JavaDataModel.getModelForSystem());
  }
