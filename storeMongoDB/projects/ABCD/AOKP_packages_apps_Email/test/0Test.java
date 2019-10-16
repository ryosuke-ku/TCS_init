    public void testSchemeNamesAreMoreOrLessUnique() {
        assertEquals(
                SSLUtils.escapeForSchemeName("name with spaces"),
                SSLUtils.escapeForSchemeName("name with spaces"));

        // As expected, all escaping is case insensitive.
        assertEquals(
                SSLUtils.escapeForSchemeName("NAME with spaces"),
                SSLUtils.escapeForSchemeName("name with spaces"));

        Random random = new Random(314159 /* seed */);
        for (int i = 0; i < 100; i++) {
            // Other strings should more or less be unique.
            String s1 = randomString(random);
            String s2 = randomString(random);
            MoreAsserts.assertNotEqual(
                    SSLUtils.escapeForSchemeName(s1),
                    SSLUtils.escapeForSchemeName(s2));
        }
    }
