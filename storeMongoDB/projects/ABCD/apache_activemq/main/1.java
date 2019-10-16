    synchronized public void unlock() {
        if (DISABLE_FILE_LOCK) {
            return;
        }

        lockCounter--;
        if (lockCounter != 0) {
            return;
        }

        // release the lock..
        if (lock != null) {
            try {
                lock.release();
            } catch (Throwable ignore) {
            } finally {
                if (lockSystemPropertyName != null) {
                    System.getProperties().remove(lockSystemPropertyName);
                }
                lock = null;
            }
        }
        closeReadFile();

        if (locked && deleteOnUnlock) {
            file.delete();
        }
    }
