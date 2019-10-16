    public void testUsingConfiguredDefaultCreator() throws IOException {
        File dir = folder.newFolder();
        String packageNames = "foo.baa,baz.boo";
        String locale = "en-us";
        String tempDirectoryPath = dir.getPath();
        properties = DefaultApplicationProperties.properties(packageNames, tempDirectoryPath,
                locale);
        Collection<String> actualPackageNames = properties.getComponentPackageNames();
        assertThat(actualPackageNames.size(), is(3));
        assertThat(actualPackageNames.containsAll(Arrays.asList("foo.baa", "baz.boo",
                "org.analogweb")), is(true));
        assertThat(properties.getTempDir().getPath(),
                startsWith(new File(dir.getPath() + SystemProperties.fileSeparator()
                        + Application.class.getCanonicalName()).getPath()));
        assertThat(properties.getDefaultClientLocale(), is(Locale.US));
    }
