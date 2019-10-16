    public boolean delete() {
        try {
            Libcore.os.remove(path);
            return true;
        } catch (ErrnoException errnoException) {
            return false;
        }
    }
