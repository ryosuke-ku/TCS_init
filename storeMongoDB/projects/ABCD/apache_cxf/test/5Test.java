    public void testMintFromServiceName() {
        registry.register(resolver1);
        registry.register(resolver2);
        resolver1.mint(serviceName);
        EasyMock.expectLastCall().andReturn(logical);
        control.replay();
     
        EndpointReferenceType minted = registry.mint(serviceName);
        
        control.verify();
        assertSame("unexpected minted EPR", logical, minted);
        
        control.reset();
        resolver1.mint(serviceName);
        EasyMock.expectLastCall().andReturn(null);
        resolver2.mint(serviceName);
        EasyMock.expectLastCall().andReturn(logical);
        control.replay();
        
        minted = registry.mint(serviceName);
        
        control.verify();
        assertSame("unexpected minted EPR", logical, minted);

        control.reset();
        resolver1.mint(serviceName);
        EasyMock.expectLastCall().andReturn(null);
        resolver2.mint(serviceName);
        EasyMock.expectLastCall().andReturn(null);
        control.replay();

        minted = registry.mint(serviceName);

        control.verify();
        assertNull("unexpected minted EPR", minted);
    }
