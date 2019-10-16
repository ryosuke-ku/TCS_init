    public boolean mkdir() {
        try {
            // On Android, we don't want default permissions to allow global access.
            Libcore.os.mkdir(path, S_IRWXU);
            return true;
        } catch (ErrnoException errnoException) {
            return false;
        }
    }
