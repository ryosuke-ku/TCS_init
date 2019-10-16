    public boolean renameTo(File newPath) {
        try {
            Libcore.os.rename(path, newPath.path);
            return true;
        } catch (ErrnoException errnoException) {
            return false;
        }
    }
