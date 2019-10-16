    public void testCreateUniqueFileWithPercent() throws Exception {
        final File directory =
                new IsolatedContext(new MockContentResolver(), getContext()).getFilesDir();

        final File created1 =
                Utility.createUniqueFileInternal(getTrueFileCreator(), directory, "file%s");
        assertNotNull(created1);

        final File created2 =
                Utility.createUniqueFileInternal(getTrueFileCreator(), directory, "file%s.ext");
        assertNotNull(created2);
    }
