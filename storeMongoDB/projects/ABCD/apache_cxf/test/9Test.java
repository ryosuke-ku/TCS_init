    public void testLoadInterface() {
        Extension e = new Extension();
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        e.setInterfaceName("no.such.Extension");        
        try {
            e.loadInterface(cl);                  
        } catch (ExtensionException ex) {
            assertTrue("ExtensionException does not wrap ClassNotFoundException",
                       ex.getCause() instanceof ClassNotFoundException);
        }
        
        e.setInterfaceName(Assert.class.getName());
        Class<?> cls = e.loadInterface(cl);
        assertNotNull(cls);
    }
