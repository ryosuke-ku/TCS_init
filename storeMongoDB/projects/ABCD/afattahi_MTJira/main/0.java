    public void loadProperties() throws Exception {
     	
    	String externalFileName = System.getProperty("app.properties");
    	System.out.println(externalFileName);
    	InputStream in = new FileInputStream(new File(externalFileName));
    	
    	Properties result = null;
    	
        result = new Properties ();
        result.load (in); // Can throw IOException
        System.out.println(result.toString());
        AppProperties.setProperties(result);
   
    }
