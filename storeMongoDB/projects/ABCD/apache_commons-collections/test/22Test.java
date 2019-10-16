    public void testStringValueTransformer() {
        assertNotNull( "StringValueTransformer should NEVER return a null value.",
           TransformerUtils.stringValueTransformer().transform(null));
        assertEquals( "StringValueTransformer should return \"null\" when given a null argument.", "null",
            TransformerUtils.stringValueTransformer().transform(null));
        assertEquals( "StringValueTransformer should return toString value", "6",
            TransformerUtils.stringValueTransformer().transform(Integer.valueOf(6)));
    }
