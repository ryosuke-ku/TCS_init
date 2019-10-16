    public byte[] fetch(String file) {
        try {
            final byte[] fileContent = readFromFile(getAbsoluteName(file));
            final byte[] txContent = this.txCache.get(file);
            if (fileContent == null) {
                return txContent;
            } else {
                if (txContent == null) {
                    return fileContent;
                } else {
                    return concat(fileContent, txContent);
                }
            }
        } catch (IOException ex) {
            throw new IllegalStateException("Cannot access file: " + getAbsoluteName(file), ex);
        }
    }
