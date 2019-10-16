    public void testUsingArgsCreator() throws IOException {
        properties = DefaultApplicationProperties.properties(new String[] { "arg1", "arg2=arg3",
                "analogweb.tmpdir=/foo/baa/baz" });
        Collection<String> actualPackageNames = properties.getComponentPackageNames();
        assertThat(actualPackageNames.size(), is(1));
        assertThat(actualPackageNames.contains(Application.class.getPackage().getName()), is(true));
        assertThat(properties.getTempDir().getPath(), is(new File("/foo/baa/baz").getPath()));
        assertThat(properties.getProperty("arg1"), is((Object) Boolean.TRUE));
        assertThat(properties.getStringProperty("arg2"), is("arg3"));
    }
