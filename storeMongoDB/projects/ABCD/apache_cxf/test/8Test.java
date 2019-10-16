    public void testLoad() throws ExtensionException {
        Extension e = new Extension();
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        e.setClassname("no.such.Extension");        
        try {
            e.load(cl, null);                  
        } catch (ExtensionException ex) {
            assertTrue("ExtensionException does not wrap ClassNotFoundException",
                       ex.getCause() instanceof ClassNotFoundException);
        }

        e.setClassname("java.lang.System");
        try {
            e.load(cl, null);                  
        } catch (ExtensionException ex) {
            assertTrue("ExtensionException does not wrap NoSuchMethodException " + ex.getCause(),
                       ex.getCause() instanceof NoSuchMethodException);
        } 
        e.setClassname(MyServiceConstructorThrowsException.class.getName());
        try {
            e.load(cl, null);                  
        } catch (ExtensionException ex) {
            assertTrue("ExtensionException does not wrap IllegalArgumentException",
                       ex.getCause() instanceof IllegalArgumentException);
        } 
        e.setClassname("java.lang.String");
        Object obj = e.load(cl, null);
        assertTrue("Object is not type String", obj instanceof String);        
    }
