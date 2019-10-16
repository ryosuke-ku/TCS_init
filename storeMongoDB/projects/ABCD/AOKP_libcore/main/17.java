    public void installCertificate(X509Certificate cert) throws IOException, CertificateException {
        if (cert == null) {
            throw new NullPointerException("cert == null");
        }
        File system = getCertificateFile(systemDir, cert);
        if (system.exists()) {
            File deleted = getCertificateFile(deletedDir, cert);
            if (deleted.exists()) {
                // we have a system cert that was marked deleted.
                // remove the deleted marker to expose the original
                if (!deleted.delete()) {
                    throw new IOException("Could not remove " + deleted);
                }
                return;
            }
            // otherwise we just have a dup of an existing system cert.
            // return taking no further action.
            return;
        }
        File user = getCertificateFile(addedDir, cert);
        if (user.exists()) {
            // we have an already installed user cert, bail.
            return;
        }
        // install the user cert
        writeCertificate(user, cert);
    }
