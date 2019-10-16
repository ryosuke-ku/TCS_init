    public void testMutators() {
        Extension e = new Extension();
        
        String className = "org.apache.cxf.bindings.soap.SoapBinding";
        e.setClassname(className);
        assertEquals("Unexpected class name.", className, e.getClassname());
        assertNull("Unexpected interface name.", e.getInterfaceName());
        
        String interfaceName = "org.apache.cxf.bindings.Binding";
        e.setInterfaceName(interfaceName);
        assertEquals("Unexpected interface name.", interfaceName, e.getInterfaceName());
        
        assertTrue("Extension is deferred.", !e.isDeferred());
        e.setDeferred(true);
        assertTrue("Extension is not deferred.", e.isDeferred());
        
        assertEquals("Unexpected size of namespace list.", 0, e.getNamespaces().size());
    }
