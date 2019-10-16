    public void testWriteObjects() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (AbstractTriplet triplet : TRIPLETS) {
            triplet.writeToStream(baos);
        }
        byte[] expected = baos.toByteArray();
        baos.reset();
        getSut().writeTriplets(baos);
        assertTrue(Arrays.equals(expected, baos.toByteArray()));

        baos.reset();
        // Ensure it doesn't die if no data has been added
        emptyStructuredObject.writeTriplets(baos);
        byte[] emptyArray = baos.toByteArray();
        assertTrue(Arrays.equals(emptyArray, new byte[0]));
    }
