    public void testCommandMap() {
        Map<String, String> map = new HashMap<String, String>();
        // Condition Commands
        // RFC3082 - Implementations MUST support these:
        map.put("if", "org.apache.jsieve.commands.If");
        map.put("else", "org.apache.jsieve.commands.Else");
        map.put("elsif", "org.apache.jsieve.commands.Elsif");
        map.put("require", "org.apache.jsieve.commands.Require");
        map.put("stop", "org.apache.jsieve.commands.Stop");

        // Action Commands
        // RFC3082 - Implementations MUST support these:
        map.put("keep", "org.apache.jsieve.commands.Keep");
        map.put("discard", "org.apache.jsieve.commands.Discard");
        map.put("redirect", "org.apache.jsieve.commands.Redirect");
        // RFC3082 - Implementations SHOULD support these:
        map.put("reject", "org.apache.jsieve.commands.optional.Reject");
        map.put("fileinto", "org.apache.jsieve.commands.optional.FileInto");

        boolean isTestPassed = false;
        try {
            Map commandMap = new ConfigurationManager().getCommandMap();

            for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
                Map.Entry entry = (Map.Entry) stringStringEntry;
                Assert.assertTrue("Key: " + entry.getKey(), commandMap
                        .containsKey(entry.getKey()));
                Assert.assertTrue("Value: " + entry.getValue(), commandMap.get(
                        entry.getKey()).equals(entry.getValue()));
            }
            isTestPassed = true;
        } catch (SieveConfigurationException e) {
        }
        Assert.assertTrue(isTestPassed);
    }
