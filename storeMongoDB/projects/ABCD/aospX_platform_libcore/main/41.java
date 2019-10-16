    public long getFreeSpace() {
        try {
            StructStatFs sb = Libcore.os.statfs(path);
            return sb.f_bfree * sb.f_bsize; // free block count * block size in bytes.
        } catch (ErrnoException errnoException) {
            return 0;
        }
    }
