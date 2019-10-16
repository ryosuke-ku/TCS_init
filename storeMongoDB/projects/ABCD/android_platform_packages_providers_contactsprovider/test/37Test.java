    public static void assertUriEquals(Uri expected, Uri actual) {
        assertEquals(expected.getScheme(), actual.getScheme());
        assertEquals(expected.getAuthority(), actual.getAuthority());
        assertEquals(expected.getPath(), actual.getPath());
        assertEquals(expected.getFragment(), actual.getFragment());
        Set<String> expectedParameterNames = expected.getQueryParameterNames();
        Set<String> actualParameterNames = actual.getQueryParameterNames();
        assertEquals(expectedParameterNames.size(), actualParameterNames.size());
        assertTrue(expectedParameterNames.containsAll(actualParameterNames));
        for (String parameterName : expectedParameterNames) {
            assertEquals(expected.getQueryParameter(parameterName),
                    actual.getQueryParameter(parameterName));
        }

    }
