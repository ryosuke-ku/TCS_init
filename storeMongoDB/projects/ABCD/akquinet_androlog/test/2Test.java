    public void testActivationFromProperties() throws FileNotFoundException,
            IOException {
        File config = new File("src/test/resources/androlog-active.properties");

        Properties props = new Properties();
        props.load(new FileInputStream(config));
        Log.reset();
        Log.configure(props);

        assertTrue(Log.isLoggable("any", Constants.INFO));
        assertTrue(Log.isLoggable(this, Constants.INFO));

    }
