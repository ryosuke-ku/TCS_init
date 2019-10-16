    public void testTransformedPredicate() {
        assertEquals(true, PredicateUtils.transformedPredicate(
                TransformerUtils.nopTransformer(),
                TruePredicate.truePredicate()).evaluate(new Object()));

        final Map<Object, Object> map = new HashMap<Object, Object>();
        map.put(Boolean.TRUE, "Hello");
        final Transformer<Object, Object> t = TransformerUtils.mapTransformer(map);
        final Predicate<Object> p = EqualPredicate.<Object>equalPredicate("Hello");
        assertEquals(false, PredicateUtils.transformedPredicate(t, p).evaluate(null));
        assertEquals(true, PredicateUtils.transformedPredicate(t, p).evaluate(Boolean.TRUE));
        try {
            PredicateUtils.transformedPredicate(null, null);
            fail();
        } catch (final NullPointerException ex) {}
    }
