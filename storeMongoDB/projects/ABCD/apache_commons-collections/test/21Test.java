    public void testInvokerTransformer2() {
        final List<Object> list = new ArrayList<Object>();
        assertEquals(Boolean.FALSE, TransformerUtils.invokerTransformer("contains",
                new Class[] { Object.class }, new Object[] { cString }).transform(list));
        list.add(cString);
        assertEquals(Boolean.TRUE, TransformerUtils.invokerTransformer("contains",
                new Class[] { Object.class }, new Object[] { cString }).transform(list));
        assertEquals(null, TransformerUtils.invokerTransformer("contains",
                new Class[] { Object.class }, new Object[] { cString }).transform(null));

        try {
            TransformerUtils.invokerTransformer(null, null, null);
            fail();
        } catch (final NullPointerException ex) {}
        try {
            TransformerUtils.invokerTransformer("noSuchMethod", new Class[] { Object.class },
                    new Object[] { cString }).transform(new Object());
            fail();
        } catch (final FunctorException ex) {}
        try {
            TransformerUtils.invokerTransformer("badArgs", null, new Object[] { cString });
            fail();
        } catch (final IllegalArgumentException ex) {}
        try {
            TransformerUtils.invokerTransformer("badArgs", new Class[] { Object.class }, null);
            fail();
        } catch (final IllegalArgumentException ex) {}
        try {
            TransformerUtils.invokerTransformer("badArgs", new Class[] {}, new Object[] { cString });
            fail();
        } catch (final IllegalArgumentException ex) {}
    }
