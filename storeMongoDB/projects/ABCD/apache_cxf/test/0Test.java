    public void testGetContactLocation() {
        registry.register(resolver1);
        registry.register(resolver2);
        resolver1.getContractLocation(serviceName);
        EasyMock.expectLastCall().andReturn(uri1);
        control.replay();
     
        URI resolved = registry.getContractLocation(serviceName);
        
        control.verify();
        assertSame("unexpected physical EPR", uri1, resolved);
        
        control.reset();
        resolver1.getContractLocation(serviceName);
        EasyMock.expectLastCall().andReturn(null);
        resolver2.getContractLocation(serviceName);
        EasyMock.expectLastCall().andReturn(uri2);
        control.replay();
        
        resolved = registry.getContractLocation(serviceName);
        
        control.verify();
        assertSame("unexpected physical EPR", uri2, resolved);
        assertNotSame("unexpected physical EPR", uri1, resolved);

        control.reset();
        resolver1.getContractLocation(serviceName);
        EasyMock.expectLastCall().andReturn(null);
        resolver2.getContractLocation(serviceName);
        EasyMock.expectLastCall().andReturn(null);
        control.replay();

        resolved = registry.getContractLocation(serviceName);

        control.verify();
        assertNull("unexpected physical EPR", resolved);
    }
