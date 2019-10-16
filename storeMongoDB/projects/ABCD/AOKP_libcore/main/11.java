    public boolean isUserAddedCertificate(X509Certificate cert) {
        return getCertificateFile(addedDir, cert).exists();
    }
