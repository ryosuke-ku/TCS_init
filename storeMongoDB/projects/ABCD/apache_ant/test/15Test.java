    public void testDuplicateTargetsImport() {
        // overriding target from imported buildfile is allowed
        buildRule.configureProject("src/etc/testcases/core/duplicate-target2.xml");
        buildRule.executeTarget("once");
        assertContains("once from buildfile", buildRule.getLog());
    }
