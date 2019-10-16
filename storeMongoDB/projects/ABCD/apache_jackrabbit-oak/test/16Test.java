    public void testSyncPropertiesMappingConstants() throws Exception {
        ExternalUser externalUser = idp.getUser(TestIdentityProvider.ID_SECOND_USER);
        sync(externalUser);

        Authorizable a = userManager.getAuthorizable(externalUser.getId());

        // create mapping that doesn't match to names in the external-properties
        // -> previously synced properties must be removed
        Map<String, String> mapping = new HashMap();
        Map<String, ?> extProps = externalUser.getProperties();
        for (String propName : extProps.keySet()) {
            mapping.put(propName, "\"any\"");
        }

        syncCtx.syncProperties(externalUser, a, mapping);
        Value anyValue = valueFactory.createValue("any");
        for (String propName : extProps.keySet()) {
            assertTrue(a.hasProperty(propName));
            assertEquals(anyValue, a.getProperty(propName)[0]);
        }
    }
