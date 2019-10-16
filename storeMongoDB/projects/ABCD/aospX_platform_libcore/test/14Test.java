    public void test_emptyFilename() throws Exception {
        // The behavior of the empty filename is an odd mixture.
        File f = new File("");
        // Mostly it behaves like an invalid path...
        assertFalse(f.canExecute());
        assertFalse(f.canRead());
        assertFalse(f.canWrite());
        try {
            f.createNewFile();
            fail("expected IOException");
        } catch (IOException expected) {
        }
        assertFalse(f.delete());
        f.deleteOnExit();
        assertFalse(f.exists());
        assertEquals("", f.getName());
        assertEquals(null, f.getParent());
        assertEquals(null, f.getParentFile());
        assertEquals("", f.getPath());
        assertFalse(f.isAbsolute());
        assertFalse(f.isDirectory());
        assertFalse(f.isFile());
        assertFalse(f.isHidden());
        assertEquals(0, f.lastModified());
        assertEquals(0, f.length());
        assertEquals(null, f.list());
        assertEquals(null, f.list(null));
        assertEquals(null, f.listFiles());
        assertEquals(null, f.listFiles((FileFilter) null));
        assertEquals(null, f.listFiles((FilenameFilter) null));
        assertFalse(f.mkdir());
        assertFalse(f.mkdirs());
        assertFalse(f.renameTo(f));
        assertFalse(f.setLastModified(123));
        assertFalse(f.setExecutable(true));
        assertFalse(f.setReadOnly());
        assertFalse(f.setReadable(true));
        assertFalse(f.setWritable(true));
        // ...but sometimes it behaves like "user.dir".
        String cwd = System.getProperty("user.dir");
        assertEquals(new File(cwd), f.getAbsoluteFile());
        assertEquals(cwd, f.getAbsolutePath());
        // TODO: how do we test these without hard-coding assumptions about where our temporary
        // directory is? (In practice, on Android, our temporary directory is accessed through
        // a symbolic link, so the canonical file/path will be different.)
        //assertEquals(new File(cwd), f.getCanonicalFile());
        //assertEquals(cwd, f.getCanonicalPath());
    }
