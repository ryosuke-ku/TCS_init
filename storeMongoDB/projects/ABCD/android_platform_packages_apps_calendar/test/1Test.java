    public void testGetAddressesFromList() {
        mActivity = buildTestContext();
        mHelper = new EditEventHelper(mActivity, null);

        LinkedHashSet<Rfc822Token> expected = new LinkedHashSet<Rfc822Token>();
        expected.add(new Rfc822Token(null, "ad1@email.com", ""));
        expected.add(new Rfc822Token("First Last", "first@email.com", "comment"));
        expected.add(new Rfc822Token(null, "one.two.three@email.grue", ""));

        LinkedHashSet<Rfc822Token> actual = mHelper.getAddressesFromList(TEST_ADDRESSES,
                new Rfc822Validator(null));
        assertEquals(expected, actual);
    }
