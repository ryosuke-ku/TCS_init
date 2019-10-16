    public void testPurge() throws Exception {
        List<MemoryFile> files = new ArrayList<MemoryFile>();
        try {
            while (true) {
                // This will fail if the process runs out of file descriptors before
                // the kernel starts purging ashmem areas.
                MemoryFile newFile = new MemoryFile("MemoryFileTest", 10000000);
                newFile.allowPurging(true);
                newFile.writeBytes(testString, 0, 0, testString.length);
                files.add(newFile);
                for (MemoryFile file : files) {
                    try {
                        file.readBytes(testString, 0, 0, testString.length);
                    } catch (IOException e) {
                        // Expected
                        return;
                    }
                }
            }
        } finally {
            for (MemoryFile fileToClose : files) {
                fileToClose.close();
            }
        }
    }
