    public void scan() throws IllegalStateException {
        synchronized (scanLock) {
            if (scanning) {
                while (scanning) {
                    try {
                        scanLock.wait();
                    } catch (final InterruptedException ignored) {
                    }
                }
                if (illegal != null) {
                    throw illegal;
                }
                return;
            }
            scanning = true;
        }
        final File savedBase = basedir;
        try {
            synchronized (this) {
                illegal = null;
                clearResults();

                // set in/excludes to reasonable defaults if needed:
                final boolean nullIncludes = includes == null;
                includes = nullIncludes ? new String[] {SelectorUtils.DEEP_TREE_MATCH} : includes;
                final boolean nullExcludes = excludes == null;
                excludes = nullExcludes ? new String[0] : excludes;

                if (basedir != null && !followSymlinks
                    && Files.isSymbolicLink(basedir.toPath())) {
                    notFollowedSymlinks.add(basedir.getAbsolutePath());
                    basedir = null;
                }

                if (basedir == null) {
                    // if no basedir and no includes, nothing to do:
                    if (nullIncludes) {
                        return;
                    }
                } else {
                    if (!basedir.exists()) {
                        if (errorOnMissingDir) {
                            illegal = new IllegalStateException("basedir "
                                                                + basedir
                                                                + DOES_NOT_EXIST_POSTFIX);
                        } else {
                            // Nothing to do - basedir does not exist
                            return;
                        }
                    } else if (!basedir.isDirectory()) {
                        illegal = new IllegalStateException("basedir "
                                                            + basedir
                                                            + " is not a directory.");
                    }
                    if (illegal != null) {
                        throw illegal;
                    }
                }
                if (isIncluded(TokenizedPath.EMPTY_PATH)) {
                    if (isExcluded(TokenizedPath.EMPTY_PATH)) {
                        dirsExcluded.addElement("");
                    } else if (isSelected("", basedir)) {
                        dirsIncluded.addElement("");
                    } else {
                        dirsDeselected.addElement("");
                    }
                } else {
                    dirsNotIncluded.addElement("");
                }
                checkIncludePatterns();
                clearCaches();
                includes = nullIncludes ? null : includes;
                excludes = nullExcludes ? null : excludes;
            }
        } finally {
            basedir = savedBase;
            synchronized (scanLock) {
                scanning = false;
                scanLock.notifyAll();
            }
        }
    }
