    public boolean createNewFile() throws IOException {
        FileDescriptor fd = null;
        try {
            // On Android, we don't want default permissions to allow global access.
            fd = Libcore.os.open(path, O_RDWR | O_CREAT | O_EXCL, 0600);
            return true;
        } catch (ErrnoException errnoException) {
            if (errnoException.errno == EEXIST) {
                // The file already exists.
                return false;
            }
            throw errnoException.rethrowAsIOException();
        } finally {
            IoUtils.close(fd); // TODO: should we suppress IOExceptions thrown here?
        }
    }
