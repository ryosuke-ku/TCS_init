    private void cleanStore() {
        for (File dir : new File[] { DIR_SYSTEM, DIR_ADDED, DIR_DELETED, DIR_TEST }) {
            File[] files = dir.listFiles();
            if (files == null) {
                continue;
            }
            for (File file : files) {
                assertTrue(file.delete());
            }
        }
        store = null;
    }
