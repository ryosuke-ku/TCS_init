    public void testGetTripletLength() {

        int dataLength = 0;
        for (Triplet t : TRIPLETS) {
            dataLength += t.getDataLength();
        }
        assertEquals(dataLength, getSut().getTripletDataLength());
        assertEquals(0, emptyStructuredObject.getTripletDataLength());
    }
