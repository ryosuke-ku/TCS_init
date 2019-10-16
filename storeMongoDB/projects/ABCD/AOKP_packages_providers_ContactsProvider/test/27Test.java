    private void assertQueryParameter(String uriString, String parameter, String expectedValue) {
        assertEquals(expectedValue, ContactsProvider2.getQueryParameter(
                Uri.parse(uriString), parameter));
    }
