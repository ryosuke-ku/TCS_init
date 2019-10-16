    public void deleteCertificateEntry(String alias) throws IOException, CertificateException {
        if (alias == null) {
            return;
        }
        File file = fileForAlias(alias);
        if (file == null) {
            return;
        }
        if (isSystem(alias)) {
            X509Certificate cert = readCertificate(file);
            if (cert == null) {
                // skip problem certificates
                return;
            }
            File deleted = getCertificateFile(deletedDir, cert);
            if (deleted.exists()) {
                // already deleted system certificate
                return;
            }
            // write copy of system cert to marked as deleted
            writeCertificate(deleted, cert);
            return;
        }
        if (isUser(alias)) {
            // truncate the file to make a tombstone by opening and closing.
            // we need ensure that we don't leave a gap before a valid cert.
            new FileOutputStream(file).close();
            removeUnnecessaryTombstones(alias);
            return;
        }
        // non-existant user cert, nothing to delete
    }
