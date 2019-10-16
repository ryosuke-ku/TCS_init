    public void close() {
        try {
            this.closeable.close();
        } catch (IOException ex) {
            throw new IllegalStateException("Cannot close GenericManagedConnection",ex);
        }
    }
