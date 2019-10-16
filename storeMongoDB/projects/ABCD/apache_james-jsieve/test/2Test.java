    public void testComparatorMap() {
        Map<String, String> map = new HashMap<String, String>();

        // RFC3082 - Required Comparators
        map.put("i;octet", "org.apache.jsieve.comparators.Octet");
        map
                .put("i;ascii-casemap",
                        "org.apache.jsieve.comparators.AsciiCasemap");

        boolean isTestPassed = false;
        try {
            Map comparatorMap = new ConfigurationManager().getComparatorMap();

            for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
                Map.Entry entry = (Map.Entry) stringStringEntry;
                Assert.assertTrue("Key: " + entry.getKey(), comparatorMap
                        .containsKey(entry.getKey()));
                Assert.assertTrue("Value: " + entry.getValue(), comparatorMap.get(
                        entry.getKey()).equals(entry.getValue()));
            }
            isTestPassed = true;
        } catch (SieveConfigurationException e) {
        }
        Assert.assertTrue(isTestPassed);
    }
