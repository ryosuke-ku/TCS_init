    public void testAsPredicateTransformerEx2() {
        PredicateUtils.asPredicate(TransformerUtils.<Boolean>nopTransformer()).evaluate(null);
    }
