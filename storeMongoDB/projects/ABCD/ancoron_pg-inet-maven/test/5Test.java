    private void assertAddNegative(String ipa, String offset) {
        IPTarget a = new IPTarget(ipa);
        BigInteger off = new BigInteger(offset);

        try {
            IPTarget b = a.add(off);
            Assert.fail("Expected an IllegalArgumentException but got a result: " + b);
        } catch(IllegalArgumentException x) {
            // expected...
        }
    }
