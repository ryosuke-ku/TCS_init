    public void testSwitchTransformer() {
        final Transformer<String, String> a = TransformerUtils.constantTransformer("A");
        final Transformer<String, String> b = TransformerUtils.constantTransformer("B");
        final Transformer<String, String> c = TransformerUtils.constantTransformer("C");

        assertEquals("A", TransformerUtils.switchTransformer(TruePredicate.truePredicate(), a, b).transform(null));
        assertEquals("B", TransformerUtils.switchTransformer(FalsePredicate.falsePredicate(), a, b).transform(null));

        assertEquals(null, TransformerUtils.<Object, String>switchTransformer(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Transformer[] { a, b }).transform("WELL"));
        assertEquals("A", TransformerUtils.switchTransformer(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Transformer[] { a, b }).transform("HELLO"));
        assertEquals("B", TransformerUtils.switchTransformer(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Transformer[] { a, b }).transform("THERE"));

        assertEquals("C", TransformerUtils.switchTransformer(
            new Predicate[] { EqualPredicate.equalPredicate("HELLO"), EqualPredicate.equalPredicate("THERE") },
            new Transformer[] { a, b }, c).transform("WELL"));

        Map<Predicate<String>, Transformer<String, String>> map = new HashMap<Predicate<String>, Transformer<String,String>>();
        map.put(EqualPredicate.equalPredicate("HELLO"), a);
        map.put(EqualPredicate.equalPredicate("THERE"), b);
        assertEquals(null, TransformerUtils.switchTransformer(map).transform("WELL"));
        assertEquals("A", TransformerUtils.switchTransformer(map).transform("HELLO"));
        assertEquals("B", TransformerUtils.switchTransformer(map).transform("THERE"));
        map.put(null, c);
        assertEquals("C", TransformerUtils.switchTransformer(map).transform("WELL"));

        assertEquals(ConstantTransformer.NULL_INSTANCE, TransformerUtils.switchTransformer(new Predicate[0], new Transformer[0]));
        assertEquals(ConstantTransformer.NULL_INSTANCE, TransformerUtils.switchTransformer(new HashMap<Predicate<Object>, Transformer<Object, Object>>()));
        map = new HashMap<Predicate<String>, Transformer<String, String>>();
        map.put(null, null);
        assertEquals(ConstantTransformer.NULL_INSTANCE, TransformerUtils.switchTransformer(map));

        try {
            TransformerUtils.switchTransformer(null, null);
            fail();
        } catch (final NullPointerException ex) {}
        try {
            TransformerUtils.switchTransformer((Predicate[]) null, (Transformer[]) null);
            fail();
        } catch (final NullPointerException ex) {}
        try {
            TransformerUtils.switchTransformer((Map<Predicate<Object>, Transformer<Object, Object>>) null);
            fail();
        } catch (final NullPointerException ex) {}
        try {
            TransformerUtils.switchTransformer(new Predicate[2], new Transformer[2]);
            fail();
        } catch (final NullPointerException ex) {}
        try {
            TransformerUtils.switchTransformer(
                    new Predicate[] { TruePredicate.truePredicate() },
                    new Transformer[] { a, b });
            fail();
        } catch (final IllegalArgumentException ex) {}
    }
