    public void testMapTransformer() {
        final Map<Object, Integer> map = new HashMap<Object, Integer>();
        map.put(null, 0);
        map.put(cObject, 1);
        map.put(cString, 2);
        assertEquals(Integer.valueOf(0), TransformerUtils.mapTransformer(map).transform(null));
        assertEquals(Integer.valueOf(1), TransformerUtils.mapTransformer(map).transform(cObject));
        assertEquals(Integer.valueOf(2), TransformerUtils.mapTransformer(map).transform(cString));
        assertEquals(null, TransformerUtils.mapTransformer(map).transform(cInteger));
        assertSame(ConstantTransformer.NULL_INSTANCE, TransformerUtils.mapTransformer(null));
    }
