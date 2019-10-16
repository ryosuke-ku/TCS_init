    public void testRegister() {
        assertEquals("unexpected resolver count",
                     0,
                     registry.getResolvers().size());
        
        registry.register(resolver1);
        
        assertEquals("unexpected resolver count",
                     1,
                     registry.getResolvers().size());
        assertTrue("expected resolver to be registered",
                   registry.getResolvers().contains(resolver1));
        
        registry.unregister(resolver1);
        
        assertEquals("unexpected resolver count",
                     0,
                     registry.getResolvers().size());
        assertFalse("expected resolver to be registered",
                    registry.getResolvers().contains(resolver1));
        
        registry.register(resolver2);
        registry.register(resolver1);
        
        assertEquals("unexpected resolver count",
                     2,
                     registry.getResolvers().size());
        assertTrue("expected resolver to be registered",
                   registry.getResolvers().contains(resolver1));
        assertTrue("expected resolver to be registered",
                   registry.getResolvers().contains(resolver2));
        
        registry.unregister(resolver2);
        
        assertEquals("unexpected resolver count",
                     1,
                     registry.getResolvers().size());
        assertTrue("expected resolver to be registered",
                   registry.getResolvers().contains(resolver1));
        assertFalse("expected resolver to be registered",
                    registry.getResolvers().contains(resolver2));
    }
