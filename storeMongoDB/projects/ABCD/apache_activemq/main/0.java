    synchronized public void lock() throws IOException {
        if (DISABLE_FILE_LOCK) {
            return;
        }

        if (lockCounter > 0) {
            return;
        }

        IOHelper.mkdirs(file.getParentFile());
        synchronized (LockFile.class) {
            lockSystemPropertyName = getVmLockKey();
            if (System.getProperty(lockSystemPropertyName) != null) {
                throw new IOException("File '" + file + "' could not be locked as lock is already held for this jvm. Value: " + System.getProperty(lockSystemPropertyName));
            }
            System.setProperty(lockSystemPropertyName, new Date().toString());
        }
        try {
            if (lock == null) {
                randomAccessLockFile = new RandomAccessFile(file, "rw");
                IOException reason = null;
                try {
                    lock = randomAccessLockFile.getChannel().tryLock(0, Math.max(1, randomAccessLockFile.getChannel().size()), false);
                } catch (OverlappingFileLockException e) {
                    reason = IOExceptionSupport.create("File '" + file + "' could not be locked.", e);
                } catch (IOException ioe) {
                    reason = ioe;
                }
                if (lock != null) {
                    //track lastModified only if we are able to successfully obtain the lock.
                    randomAccessLockFile.writeLong(System.currentTimeMillis());
                    randomAccessLockFile.getChannel().force(true);
                    lastModified = file.lastModified();
                    lockCounter++;
                    System.setProperty(lockSystemPropertyName, new Date().toString());
                    locked = true;
                } else {
                    // new read file for next attempt
                    closeReadFile();
                    if (reason != null) {
                        throw reason;
                    }
                    throw new IOException("File '" + file + "' could not be locked.");
                }

            }
        } finally {
            synchronized (LockFile.class) {
                if (lock == null) {
                    System.getProperties().remove(lockSystemPropertyName);
                }
            }
        }
    }
