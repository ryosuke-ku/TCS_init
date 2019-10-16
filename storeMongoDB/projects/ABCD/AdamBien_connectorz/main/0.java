    public void write(String fileName, byte[] content) {
        out.println("#FileBucket.write " + fileName + " " + content);
        final byte[] existingContent = this.txCache.get(fileName);
        if (existingContent == null) {
             this.txCache.put(fileName, content);
        } else {
            this.txCache.put(fileName, concat(existingContent, content));
        }
    }
