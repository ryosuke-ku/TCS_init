    private static synchronized void initCerts() {
        if (CA1 != null) {
            return;
        }
        try {
            CA1 = TestKeyStore.getClient().getRootCertificate("RSA");
            CA2 = TestKeyStore.getClientCA2().getRootCertificate("RSA");
            PRIVATE = TestKeyStore.getServer().getPrivateKey("RSA", "RSA");
            CHAIN = (X509Certificate[]) PRIVATE.getCertificateChain();
            CA3_WITH_CA1_SUBJECT = new TestKeyStore.Builder()
                    .aliasPrefix("unused")
                    .subject(CA1.getSubjectX500Principal())
                    .ca(true)
                    .build().getRootCertificate("RSA");


            ALIAS_SYSTEM_CA1 = alias(false, CA1, 0);
            ALIAS_SYSTEM_CA2 = alias(false, CA2, 0);
            ALIAS_USER_CA1 = alias(true, CA1, 0);
            ALIAS_USER_CA2 = alias(true, CA2, 0);

            ALIAS_SYSTEM_CHAIN0 = alias(false, getChain()[0], 0);
            ALIAS_SYSTEM_CHAIN1 = alias(false, getChain()[1], 0);
            ALIAS_SYSTEM_CHAIN2 = alias(false, getChain()[2], 0);
            ALIAS_USER_CHAIN0 = alias(true, getChain()[0], 0);
            ALIAS_USER_CHAIN1 = alias(true, getChain()[1], 0);
            ALIAS_USER_CHAIN2 = alias(true, getChain()[2], 0);

            ALIAS_SYSTEM_CA3 = alias(false, CA3_WITH_CA1_SUBJECT, 0);
            ALIAS_SYSTEM_CA3_COLLISION = alias(false, CA3_WITH_CA1_SUBJECT, 1);
            ALIAS_USER_CA3 = alias(true, CA3_WITH_CA1_SUBJECT, 0);
            ALIAS_USER_CA3_COLLISION = alias(true, CA3_WITH_CA1_SUBJECT, 1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
