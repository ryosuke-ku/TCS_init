    public static Uri fromFile(File file) {
        if (file == null) {
            throw new NullPointerException("file");
        }

        PathPart path = PathPart.fromDecoded(file.getAbsolutePath());
        return new HierarchicalUri(
                "file", Part.EMPTY, path, Part.NULL, Part.NULL);
    }
