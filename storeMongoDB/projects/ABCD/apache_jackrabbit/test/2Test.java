    public void testReadHoldFromFile() throws RepositoryException {
        PathResolver resolver = (SessionImpl) superuser;
        RetentionRegistryImpl re = new RetentionRegistryImpl((SessionImpl) superuser, createFileSystem());
        try {
            assertTrue(re.hasEffectiveHold(resolver.getQPath(childNPath), false));
            assertTrue(re.hasEffectiveHold(resolver.getQPath(childN3.getPath()), false));
            assertTrue(re.hasEffectiveHold(resolver.getQPath(childNPath + "/somechild"), false));
            assertTrue(re.hasEffectiveHold(resolver.getQPath(childNPath + "/hold/is/deep"), false));

            assertFalse(re.hasEffectiveHold(resolver.getQPath(testNodePath), false));
            assertFalse(re.hasEffectiveHold(resolver.getQPath(childN2.getPath()), false));

        } finally {
            re.close();
        }
    }
