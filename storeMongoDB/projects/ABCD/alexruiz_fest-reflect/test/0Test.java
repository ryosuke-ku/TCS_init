  @Test public void should_return_raw_type() {
    TypeRef<List<String>> typeRef = new TypeRef<List<String>>() {
    };
    Class<List<String>> rawType = typeRef.rawType();
    assertEquals(List.class, rawType);
  }
