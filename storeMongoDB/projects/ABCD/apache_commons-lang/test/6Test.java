    public void testRandomAlphaNumeric() {
        final char[] testChars = {'a', 'z', 'A', 'Z', '0', '9'};
        final boolean[] found = {false, false, false, false, false, false};
        for (int i = 0; i < 100; i++) {
            final String randString = RandomStringUtils.randomAlphanumeric(10);
            for (int j = 0; j < testChars.length; j++) {
                if (randString.indexOf(testChars[j]) > 0) {
                    found[j] = true;
                }
            }
        }
        for (int i = 0; i < testChars.length; i++) {
            if (!found[i]) {
                fail("alphanumeric character not generated in 1000 attempts: " 
                   + testChars[i] +" -- repeated failures indicate a problem ");
            }
        }
    }
