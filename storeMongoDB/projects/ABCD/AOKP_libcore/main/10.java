    public String getCertificateAlias(Certificate c) {
        if (c == null || !(c instanceof X509Certificate)) {
            return null;
        }
        X509Certificate x = (X509Certificate) c;
        File user = getCertificateFile(addedDir, x);
        if (user.exists()) {
            return PREFIX_USER + user.getName();
        }
        if (isDeletedSystemCertificate(x)) {
            return null;
        }
        File system = getCertificateFile(systemDir, x);
        if (system.exists()) {
            return PREFIX_SYSTEM + system.getName();
        }
        return null;
    }
