    private void compareFiles(DirectoryScanner ds, String[] expectedFiles,
                              String[] expectedDirectories) {
        String[] includedFiles = ds.getIncludedFiles();
        String[] includedDirectories = ds.getIncludedDirectories();
        assertEquals("file present: ", expectedFiles.length,
                     includedFiles.length);
        assertEquals("directories present: ", expectedDirectories.length,
                     includedDirectories.length);

        TreeSet<String> files = Arrays.stream(includedFiles)
                .map(includedFile -> includedFile.replace(File.separatorChar, '/'))
                .collect(Collectors.toCollection(TreeSet::new));

        TreeSet<String> directories = Arrays.stream(includedDirectories)
                .map(includedDirectory -> includedDirectory.replace(File.separatorChar, '/'))
                .collect(Collectors.toCollection(TreeSet::new));

        int counter = 0;
        for (String currentFile : files) {
            assertEquals(expectedFiles[counter++], currentFile);
        }
        counter = 0;
        for (String currentDirectory : directories) {
             assertEquals(expectedDirectories[counter++], currentDirectory);
        }
    }
