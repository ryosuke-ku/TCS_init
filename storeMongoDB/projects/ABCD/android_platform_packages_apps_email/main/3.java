    public static File createUniqueFile(File directory, String filename) throws IOException {
        return createUniqueFileInternal(NewFileCreator.DEFAULT, directory, filename);
    }
