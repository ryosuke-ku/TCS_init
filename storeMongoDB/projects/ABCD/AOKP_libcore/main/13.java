    public X509Certificate findIssuer(final X509Certificate c) {
        // match on verified issuer of Certificate
        CertSelector selector = new CertSelector() {
            @Override public boolean match(X509Certificate ca) {
                try {
                    c.verify(ca.getPublicKey());
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }
        };
        X500Principal issuer = c.getIssuerX500Principal();
        X509Certificate user = findCert(addedDir, issuer, selector, X509Certificate.class);
        if (user != null) {
            return user;
        }
        X509Certificate system = findCert(systemDir, issuer, selector, X509Certificate.class);
        if (system != null && !isDeletedSystemCertificate(system)) {
            return system;
        }
        return null;
    }
