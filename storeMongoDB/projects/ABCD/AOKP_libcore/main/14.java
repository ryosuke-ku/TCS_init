    public List<X509Certificate> getCertificateChain(X509Certificate leaf)
            throws CertificateException {
        final List<OpenSSLX509Certificate> chain = new ArrayList<OpenSSLX509Certificate>();
        chain.add(convertToOpenSSLIfNeeded(leaf));

        for (int i = 0; true; i++) {
            OpenSSLX509Certificate cert = chain.get(i);
            if (isSelfIssuedCertificate(cert)) {
                break;
            }
            OpenSSLX509Certificate issuer = convertToOpenSSLIfNeeded(findIssuer(cert));
            if (issuer == null) {
                break;
            }
            chain.add(issuer);
        }

        return new ArrayList<X509Certificate>(chain);
    }
