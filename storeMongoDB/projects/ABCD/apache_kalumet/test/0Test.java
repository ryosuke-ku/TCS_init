    public void testKalumetMarshalling()
        throws Exception
    {
        Kalumet kalumet = new Kalumet();
        Property testProperty = new Property();
        testProperty.setName( "test" );
        testProperty.setValue( "test" );
        kalumet.getProperties().add( testProperty );
        Environment environment = new Environment();
        environment.setName( "test" );
        environment.setNotes( "Only for test" );
        environment.setWeblinks( "Only for test" );
        Software software = new Software();
        software.setName( "test" );
        software.setUri( "http://www.example.com/test?test=test&other=other" );
        environment.addSoftware( software );
        kalumet.addEnvironment( environment );
        kalumet.writeXMLFile( "file:./target/kalumet.xml" );
    }
