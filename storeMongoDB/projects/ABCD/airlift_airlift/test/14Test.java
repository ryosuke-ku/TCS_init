    private void verifyMetaData(ConfigurationMetadata<?> metadata, Class<?> configClass, String description, Map<String, Set<String>> attributeProperties)
            throws Exception
    {
        Assert.assertEquals(metadata.getConfigClass(), configClass);

        if (metadata.getConstructor() != null) {
            Assert.assertEquals(metadata.getConstructor(), configClass.getDeclaredConstructor());
        } else {
            try {
                configClass.getDeclaredConstructor();
                Assert.fail(String.format("Expected configClass [%s] not to have a constructor", configClass.getName()));
            } catch (NoSuchMethodException expected) {

            }
        }

        Assert.assertEquals(metadata.getAttributes().size(), attributeProperties.keySet().size());

        for (String name : attributeProperties.keySet()) {
            AttributeMetadata attribute = metadata.getAttributes().get(name);
            Assert.assertEquals(attribute.getConfigClass(), configClass);
            Set<String> namesToTest = Sets.newHashSet();
            namesToTest.add(attribute.getInjectionPoint().getProperty());
            for (ConfigurationMetadata.InjectionPointMetaData legacyInjectionPoint : attribute.getLegacyInjectionPoints()) {
                namesToTest.add(legacyInjectionPoint.getProperty());
            }
            Assert.assertEquals(namesToTest, attributeProperties.get(name));
            Assert.assertEquals(attribute.getDescription(), description);
        }
    }
