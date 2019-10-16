    public void testHasPropertyWithAnonymousClass() {
        DIDLObject didlObject = new DIDLObject() {

        };
        DIDLObject.Property property = new DIDLObject.Property.UPNP.ACTOR() {

        };
        didlObject.addProperty(property);
        assertTrue(didlObject.hasProperty(DIDLObject.Property.UPNP.ACTOR.class));
    }
