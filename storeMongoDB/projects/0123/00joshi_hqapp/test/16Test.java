    public void testIsExcludedDirectoryScanned() {
        String shareclassloader = buildRule.getProject().getProperty("tests.and.ant.share.classloader");
        // when the test is started by the build.xml of ant
        // if the property tests.and.ant.share.classloader is not set in the build.xml
        // a sysproperty with name tests.and.ant.share.classloader and value
        // ${tests.and.ant.share.classloader} will be set
        // we are trying to catch this here.
        assumeFalse("cannot execute testIsExcludedDirectoryScanned when tests are forked, "
                + "package private method called", shareclassloader == null
                        || shareclassloader.indexOf("${") == 0);
        buildRule.getProject().executeTarget("children-of-excluded-dir-setup");

        ds.setBasedir(new File(buildRule.getProject().getProperty("output")));
        ds.setExcludes(new String[] {"**/gamma/**"});
        ds.setFollowSymlinks(false);
        ds.scan();
        Set<String> set = ds.getScannedDirs();
        assertFalse("empty set", set.isEmpty());
        String s = "alpha/beta/gamma/".replace('/', File.separatorChar);
        assertThat("scanned " + s, set, not(hasItem(s)));
    }
