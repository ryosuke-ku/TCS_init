    public void load( InputStream is, Configuration configuration )
        throws IOException, InvalidConfigurationException
    {
        Properties props = new Properties();
        props.load( is );
        load( props, configuration );
    }
