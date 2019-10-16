    public void testExtendedRoster() {
        //System.setProperty("javax.net.debug", "ssl:record");
        final String[] cipherSuites = new String[] {
            //"TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA",
            //"TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA",
            //"TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA",
            //"TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA",
            "TLS_DHE_RSA_WITH_AES_256_CBC_SHA",
            "TLS_DHE_DSS_WITH_AES_256_CBC_SHA",
            //"TLS_ECDH_RSA_WITH_AES_256_CBC_SHA",
            //"TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA",
            //"TLS_RSA_WITH_CAMELLIA_256_CBC_SHA",
            "TLS_RSA_WITH_AES_256_CBC_SHA",
            //"TLS_ECDHE_ECDSA_WITH_RC4_128_SHA",
            //"TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA",
            //"TLS_ECDHE_RSA_WITH_RC4_128_SHA",
            //"TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA",
            //"TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA",
            //"TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA",
            //"TLS_DHE_DSS_WITH_RC4_128_SHA",
            "TLS_DHE_RSA_WITH_AES_128_CBC_SHA",
            //"SSL_RSA_WITH_RC4_128_MD5",
            "TLS_DHE_DSS_WITH_AES_128_CBC_SHA",
            //"TLS_ECDH_RSA_WITH_RC4_128_SHA",
            //"TLS_ECDH_RSA_WITH_AES_128_CBC_SHA",
            //"TLS_ECDH_ECDSA_WITH_RC4_128_SHA",
            //"TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA",
            //"TLS_RSA_WITH_SEED_CBC_SHA",
            //"TLS_RSA_WITH_CAMELLIA_128_CBC_SHA",
            //"TLS_RSA_WITH_RC4_128_MD5",
            //"TLS_RSA_WITH_RC4_128_SHA",
            "TLS_RSA_WITH_AES_128_CBC_SHA",
            //"TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA",
            //"TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA",
            //"TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA",
            //"TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA",
            //"TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA",
            //"TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA",
            //"SSL_RSA_FIPS_WITH_3DES_EDE_CBC_SHA",
            //"TLS_RSA_WITH_3DES_EDE_CBC_SHA",
        };
        final Collection<String> working = new ArrayList<String>();
        //for (final String cs : cipherSuites) {
            //XmppUtils.setGlobalConfig(xmppConfig(cipherSuites));
            try {
                final XMPPConnection conn = XmppUtils.persistentXmppConnection("adamfisk@gmail.com", "#@$77rq7rR", "test", 1);
                
                Packet msg = XmppUtils.extendedRoster(conn);
                System.out.println(msg.toXML());
                //msg = XmppUtils.getSharedStatus(conn);
                //System.out.println(msg.toXML());
                //System.out.println("Adding "+cs);
                //working.add(cs);
            } catch (CredentialException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        //}
        //System.out.println(working);
    }
