    public long getUsableSpace() {
        try {
            StructStatFs sb = Libcore.os.statfs(path);
            return sb.f_bavail * sb.f_bsize; // non-root free block count * block size in bytes.
        } catch (ErrnoException errnoException) {
            return 0;
        }
    }
