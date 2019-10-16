    public void testWriteFile() throws RepositoryException {
        PathResolver resolver = (SessionImpl) superuser;
        FileSystem fs = createFileSystem();
        RetentionRegistryImpl re = new RetentionRegistryImpl((SessionImpl) superuser, fs);

        try {
            // write the changes to the fs
            re.close();

            // create a new dummy registry again.
            re = new RetentionRegistryImpl((SessionImpl) superuser, fs);

            // test holds
            assertTrue(re.hasEffectiveHold(resolver.getQPath(childNPath), false));
            assertTrue(re.hasEffectiveHold(resolver.getQPath(childN3.getPath()), false));
            assertTrue(re.hasEffectiveHold(resolver.getQPath(childNPath + "/somechild"), false));
            assertTrue(re.hasEffectiveHold(resolver.getQPath(childNPath + "/hold/is/deep"), false));

            // test policies
            assertTrue(re.hasEffectiveRetention(resolver.getQPath(childNPath), false));
            assertTrue(re.hasEffectiveRetention(resolver.getQPath(childNPath + "/somechild"), true));
        } finally {
            re.close();
        }
    }
