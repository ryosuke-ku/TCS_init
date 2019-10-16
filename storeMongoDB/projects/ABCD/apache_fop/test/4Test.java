    public void testHasTriplet() {
        for (AbstractTriplet triplet : TRIPLETS) {
            assertTrue(getSut().hasTriplet(triplet.getId()));
            assertFalse(emptyStructuredObject.hasTriplet(triplet.getId()));
        }
        CommentTriplet notInSystem = new CommentTriplet((byte) 0x30, "This should return false");
        assertFalse(getSut().hasTriplet(notInSystem.getId()));
    }
