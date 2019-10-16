    public void testDeleteOnUnlockIfLocked() throws Exception {

        File lockFile = new File(IOHelper.getDefaultDataDirectory(), "lockToTest2");
        IOHelper.mkdirs(lockFile.getParentFile());
        lockFile.createNewFile();

        LockFile underTest = new LockFile(lockFile, true);

        underTest.lock();

        assertTrue("valid", underTest.keepAlive());

        underTest.unlock();

        assertFalse("file deleted on unlock", lockFile.exists());

    }
