    /* package */ static File createUniqueFileInternal(final NewFileCreator nfc,
            final File directory, final String filename) throws IOException {
        final File file = new File(directory, filename);
        if (nfc.createNewFile(file)) {
            return file;
        }
        // Get the extension of the file, if any.
        final int index = filename.lastIndexOf('.');
        final String name;
        final String extension;
        if (index != -1) {
            name = filename.substring(0, index);
            extension = filename.substring(index);
        } else {
            name = filename;
            extension = "";
        }

        for (int i = 2; i < Integer.MAX_VALUE; i++) {
            final File numberedFile =
                    new File(directory, name + "-" + Integer.toString(i) + extension);
            if (nfc.createNewFile(numberedFile)) {
                return numberedFile;
            }
        }
        return null;
    }
