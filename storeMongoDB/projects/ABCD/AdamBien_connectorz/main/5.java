    public void delete(String file) {
        this.txCache.remove(file);
        this.deletedFiles.add(file);
    }
