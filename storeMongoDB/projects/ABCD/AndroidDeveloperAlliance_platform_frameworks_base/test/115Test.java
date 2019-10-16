    public void testToStringWithPathOnly() {
        Uri.Builder builder = new Uri.Builder();

        // Not a valid path, but this came from a user's test case.
        builder.path("//foo");
        Uri uri = builder.build();
        assertEquals("//foo", uri.toString());
    }
