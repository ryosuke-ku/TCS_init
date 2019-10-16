    public long getTotalSpace() {
        try {
            StructStatFs sb = Libcore.os.statfs(path);
            return sb.f_blocks * sb.f_bsize; // total block count * block size in bytes.
        } catch (ErrnoException errnoException) {
            return 0;
        }
    }
