    private void assertBooleanUriParameter(String uriString, String parameter,
            boolean defaultValue, boolean expectedValue) {
        assertEquals(expectedValue, ContactsProvider2.readBooleanQueryParameter(
                Uri.parse(uriString), parameter, defaultValue));
    }
