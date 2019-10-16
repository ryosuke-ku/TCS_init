    public boolean isTrustAnchor(final X509Certificate c) {
        // compare X509Certificate.getPublicKey values
        CertSelector selector = new CertSelector() {
            @Override public boolean match(X509Certificate ca) {
                return ca.getPublicKey().equals(c.getPublicKey());
            }
        };
        boolean user = findCert(addedDir,
                                c.getSubjectX500Principal(),
                                selector,
                                Boolean.class);
        if (user) {
            return true;
        }
        X509Certificate system = findCert(systemDir,
                                          c.getSubjectX500Principal(),
                                          selector,
                                          X509Certificate.class);
        return system != null && !isDeletedSystemCertificate(system);
    }
