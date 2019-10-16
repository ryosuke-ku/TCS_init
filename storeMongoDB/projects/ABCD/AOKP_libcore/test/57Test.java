    private static synchronized void initCerts() {
        if (SERVER_PRIVATE_KEY != null) {
            return;
        }

        try {
            PrivateKeyEntry serverPrivateKeyEntry
                    = TestKeyStore.getServer().getPrivateKey("RSA", "RSA");
            SERVER_PRIVATE_KEY = OpenSSLKey.fromPrivateKey(serverPrivateKeyEntry.getPrivateKey());
            SERVER_CERTIFICATES = NativeCrypto.encodeCertificates(
                    serverPrivateKeyEntry.getCertificateChain());

            PrivateKeyEntry clientPrivateKeyEntry
                    = TestKeyStore.getClientCertificate().getPrivateKey("RSA", "RSA");
            CLIENT_PRIVATE_KEY = OpenSSLKey.fromPrivateKey(clientPrivateKeyEntry.getPrivateKey());
            CLIENT_CERTIFICATES = NativeCrypto.encodeCertificates(
                    clientPrivateKeyEntry.getCertificateChain());

            KeyStore ks = TestKeyStore.getClient().keyStore;
            String caCertAlias = ks.aliases().nextElement();
            X509Certificate certificate = (X509Certificate) ks.getCertificate(caCertAlias);
            X500Principal principal = certificate.getIssuerX500Principal();
            CA_PRINCIPALS = new byte[][] { principal.getEncoded() };
            initChannelIdKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
