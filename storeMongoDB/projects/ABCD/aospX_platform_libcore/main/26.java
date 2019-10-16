    public long length() {
        try {
            return Libcore.os.stat(path).st_size;
        } catch (ErrnoException errnoException) {
            // The RI returns 0 on error. (Even for errors like EACCES or ELOOP.)
            return 0;
        }
    }
