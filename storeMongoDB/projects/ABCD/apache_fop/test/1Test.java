    public void testHasTriplets() {
        assertTrue(getSut().hasTriplets());
        assertFalse(emptyStructuredObject.hasTriplets());
    }
