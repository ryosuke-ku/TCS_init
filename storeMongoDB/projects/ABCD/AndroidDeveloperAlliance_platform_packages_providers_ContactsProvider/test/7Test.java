    private void assertFullNameStyle(int expectedFullNameStyle, String fullName,
            String phoneticFamilyName, String phoneticMiddleName, String phoneticGivenName) {
        Name name = new Name();
        mNameSplitter.split(name, fullName);
        name.phoneticFamilyName = phoneticFamilyName;
        name.phoneticMiddleName = phoneticMiddleName;
        name.phoneticGivenName = phoneticGivenName;

        mNameSplitter.guessNameStyle(name);

        assertEquals(expectedFullNameStyle, name.fullNameStyle);
    }
