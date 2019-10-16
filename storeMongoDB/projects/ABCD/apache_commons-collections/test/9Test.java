    public void testChainedTransformer() {
        final Transformer<Object, Object> a = TransformerUtils.<Object, Object>constantTransformer("A");
        final Transformer<Object, Object> b = TransformerUtils.constantTransformer((Object) "B");

        assertEquals("A", TransformerUtils.chainedTransformer(b, a).transform(null));
        assertEquals("B", TransformerUtils.chainedTransformer(a, b).transform(null));
        assertEquals("A", TransformerUtils.chainedTransformer(new Transformer[] { b, a }).transform(null));
        Collection<Transformer<Object, Object>> coll = new ArrayList<Transformer<Object, Object>>();
        coll.add(b);
        coll.add(a);
        assertEquals("A", TransformerUtils.chainedTransformer(coll).transform(null));

        assertSame(NOPTransformer.INSTANCE, TransformerUtils.chainedTransformer(new Transformer[0]));
        assertSame(NOPTransformer.INSTANCE, TransformerUtils.chainedTransformer(Collections.<Transformer<Object, Object>>emptyList()));

        try {
            TransformerUtils.chainedTransformer(null, null);
            fail();
        } catch (final NullPointerException ex) {}
        try {
            TransformerUtils.chainedTransformer((Transformer[]) null);
            fail();
        } catch (final NullPointerException ex) {}
        try {
            TransformerUtils.chainedTransformer((Collection<Transformer<Object, Object>>) null);
            fail();
        } catch (final NullPointerException ex) {}
        try {
            TransformerUtils.chainedTransformer(new Transformer[] {null, null});
            fail();
        } catch (final NullPointerException ex) {}
        try {
            coll = new ArrayList<Transformer<Object, Object>>();
            coll.add(null);
            coll.add(null);
            TransformerUtils.chainedTransformer(coll);
            fail();
        } catch (final NullPointerException ex) {}
    }
