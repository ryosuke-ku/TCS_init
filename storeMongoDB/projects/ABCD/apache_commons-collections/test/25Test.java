    public void testNullIsFalsePredicate() {
        assertEquals(false, PredicateUtils.nullIsFalsePredicate(TruePredicate.truePredicate()).evaluate(null));
        assertEquals(true, PredicateUtils.nullIsFalsePredicate(TruePredicate.truePredicate()).evaluate(new Object()));
        assertEquals(false, PredicateUtils.nullIsFalsePredicate(FalsePredicate.falsePredicate()).evaluate(new Object()));
    }
