    public void testUsingDefaultCreator() throws IOException {
        properties = DefaultApplicationProperties.defaultProperties();
        Collection<String> actualPackageNames = properties.getComponentPackageNames();
        assertThat(actualPackageNames.size(), is(1));
        assertThat(actualPackageNames.contains(Application.class.getPackage().getName()), is(true));
        assertThat(properties.getTempDir().getPath(),
                startsWith(new File(SystemProperties.tmpDir() + SystemProperties.fileSeparator()
                        + Application.class.getCanonicalName()).getPath()));
    }
