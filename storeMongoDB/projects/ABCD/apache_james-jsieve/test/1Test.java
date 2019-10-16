    public void testTestMap() {
        Map<String, String> map = new HashMap<String, String>();

        // RFC3082 - Implementations MUST support these tests:
        map.put("address", "org.apache.jsieve.tests.Address");
        map.put("allof", "org.apache.jsieve.tests.AllOf");
        map.put("anyof", "org.apache.jsieve.tests.AnyOf");
        map.put("exists", "org.apache.jsieve.tests.Exists");
        map.put("false", "org.apache.jsieve.tests.False");
        map.put("header", "org.apache.jsieve.tests.Header");
        map.put("not", "org.apache.jsieve.tests.Not");
        map.put("size", "org.apache.jsieve.tests.Size");
        map.put("true", "org.apache.jsieve.tests.True");

        // RFC3082 - Implementations SHOULD support the "envelope" test.
        map.put("envelope", "org.apache.jsieve.tests.optional.Envelope");

        boolean isTestPassed = false;
        try {
            Map testMap = new ConfigurationManager().getTestMap();

            for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
                Map.Entry entry = (Map.Entry) stringStringEntry;
                Assert.assertTrue("Key: " + entry.getKey(), testMap.containsKey(entry
                        .getKey()));
                Assert.assertTrue("Value: " + entry.getValue(), testMap.get(
                        entry.getKey()).equals(entry.getValue()));
            }
            isTestPassed = true;
        } catch (SieveConfigurationException e) {
        }
        Assert.assertTrue(isTestPassed);
    }
