    public void testSwitchMapTransformer() {
        final Transformer<String, String> a = TransformerUtils.constantTransformer("A");
        final Transformer<String, String> b = TransformerUtils.constantTransformer("B");
        final Transformer<String, String> c = TransformerUtils.constantTransformer("C");

        Map<String, Transformer<String, String>> map = new HashMap<String, Transformer<String,String>>();
        map.put("HELLO", a);
        map.put("THERE", b);
        assertEquals(null, TransformerUtils.switchMapTransformer(map).transform("WELL"));
        assertEquals("A", TransformerUtils.switchMapTransformer(map).transform("HELLO"));
        assertEquals("B", TransformerUtils.switchMapTransformer(map).transform("THERE"));
        map.put(null, c);
        assertEquals("C", TransformerUtils.switchMapTransformer(map).transform("WELL"));

        assertSame(ConstantTransformer.NULL_INSTANCE, TransformerUtils.switchMapTransformer(new HashMap<Object, Transformer<Object, Object>>()));
        map = new HashMap<String, Transformer<String, String>>();
        map.put(null, null);
        assertSame(ConstantTransformer.NULL_INSTANCE, TransformerUtils.switchMapTransformer(map));

        try {
            TransformerUtils.switchMapTransformer(null);
            fail();
        } catch (final NullPointerException ex) {}
    }
