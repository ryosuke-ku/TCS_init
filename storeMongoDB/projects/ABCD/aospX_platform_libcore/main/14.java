    public boolean isDirectory() {
        try {
            return S_ISDIR(Libcore.os.stat(path).st_mode);
        } catch (ErrnoException errnoException) {
            // The RI returns false on error. (Even for errors like EACCES or ELOOP.)
            return false;
        }
    }
