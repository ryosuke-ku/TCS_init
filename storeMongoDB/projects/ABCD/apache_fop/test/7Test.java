    public void testAddTriplets() {
        // Tested on empty object
        List<AbstractTriplet> expectedList = TRIPLETS;
        emptyStructuredObject.addTriplets(expectedList);
        // checks equals() on each member of both lists
        assertEquals(expectedList, emptyStructuredObject.getTriplets());

        // Add a list to an already populated list
        getSut().addTriplets(expectedList);

        List<AbstractTriplet> newExpected = new ArrayList<AbstractTriplet>(expectedList);
        newExpected.addAll(expectedList);
        assertEquals(newExpected, getSut().getTriplets());

        // Ensure null doesn't throw exception
        emptyStructuredObject.addTriplets(null);
    }
