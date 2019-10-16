    static String getFullPath(String filename, String mimeType, int destination, File base)
            throws StopRequestException {
        String extension = null;
        int dotIndex = filename.lastIndexOf('.');
        boolean missingExtension = dotIndex < 0 || dotIndex < filename.lastIndexOf('/');
        if (destination == Downloads.Impl.DESTINATION_FILE_URI) {
            // Destination is explicitly set - do not change the extension
            if (missingExtension) {
                extension = "";
            } else {
                extension = filename.substring(dotIndex);
                filename = filename.substring(0, dotIndex);
            }
        } else {
            // Split filename between base and extension
            // Add an extension if filename does not have one
            if (missingExtension) {
                extension = chooseExtensionFromMimeType(mimeType, true);
            } else {
                extension = chooseExtensionFromFilename(mimeType, destination, filename, dotIndex);
                filename = filename.substring(0, dotIndex);
            }
        }

        boolean recoveryDir = Constants.RECOVERY_DIRECTORY.equalsIgnoreCase(filename + extension);

        if (base != null) {
            filename = base.getPath() + File.separator + filename;
        }

        if (Constants.LOGVV) {
            Log.v(Constants.TAG, "target file: " + filename + extension);
        }

        synchronized (sUniqueLock) {
            final String path = chooseUniqueFilenameLocked(
                    destination, filename, extension, recoveryDir);

            // Claim this filename inside lock to prevent other threads from
            // clobbering us. We're not paranoid enough to use O_EXCL.
            try {
                File file = new File(path);
                File parent = file.getParentFile();

                // Make sure the parent directories exists before generates new file
                if (parent != null && !parent.exists()) {
                    parent.mkdirs();
                }

                file.createNewFile();
            } catch (IOException e) {
                throw new StopRequestException(Downloads.Impl.STATUS_FILE_ERROR,
                        "Failed to create target file " + path, e);
            }
            return path;
        }
    }
