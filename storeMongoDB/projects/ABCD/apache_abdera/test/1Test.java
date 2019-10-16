    public void testType() throws Exception {
        Abdera abdera = Abdera.getInstance();
        Feature feature = abdera.getFactory().newElement(FeaturesHelper.FEATURE);
        feature.addType("image/jpg", "image/gif", "image/png", "image/*");
        String[] types = feature.getTypes();
        assertEquals(1, types.length);
        assertEquals("image/*", types[0]);
    }
