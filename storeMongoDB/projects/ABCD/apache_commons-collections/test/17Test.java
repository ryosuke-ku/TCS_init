    public void testInstantiateTransformerNull() {
        try {
            TransformerUtils.instantiateTransformer(null, new Object[] { "str" });
            fail();
        } catch (final IllegalArgumentException ex) {}
        try {
            TransformerUtils.instantiateTransformer(new Class[] {}, new Object[] { "str" });
            fail();
        } catch (final IllegalArgumentException ex) {}

        Transformer<Class<?>, Object> trans = TransformerUtils.instantiateTransformer(new Class[] { Long.class }, new Object[] { null });
        try {
            trans.transform(String.class);
            fail();
        } catch (final FunctorException ex) {}

        trans = TransformerUtils.instantiateTransformer();
        assertEquals("", trans.transform(String.class));

        trans = TransformerUtils.instantiateTransformer(new Class[] { Long.TYPE }, new Object[] { new Long(1000L) });
        assertEquals(new Date(1000L), trans.transform(Date.class));
    }
