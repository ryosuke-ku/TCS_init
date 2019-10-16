    public void test_SSL_set_cipher_lists() throws Exception {
        try {
            NativeCrypto.SSL_set_cipher_lists(NULL, null);
            fail();
        } catch (NullPointerException expected) {
        }

        long c = NativeCrypto.SSL_CTX_new();
        long s = NativeCrypto.SSL_new(c);

        try {
            NativeCrypto.SSL_set_cipher_lists(s, null);
            fail();
        } catch (NullPointerException expected) {
        }

        NativeCrypto.SSL_set_cipher_lists(s, new String[] {});

        try {
            NativeCrypto.SSL_set_cipher_lists(s, new String[] { null });
            fail();
        } catch (NullPointerException expected) {
        }

        // see OpenSSL ciphers man page
        String[] illegals = new String[] {
            // empty
            "",
            // never standardized
            "EXP1024-DES-CBC-SHA", "EXP1024-RC4-SHA", "DHE-DSS-RC4-SHA",
            // IDEA
            "IDEA-CBC-SHA", "IDEA-CBC-MD5"
        };

        for (String illegal : illegals) {
            try {
                NativeCrypto.SSL_set_cipher_lists(s, new String[] { illegal });
                fail(illegal);
            } catch (IllegalArgumentException expected) {
            }
        }

        List<String> ciphers
                = new ArrayList<String>(NativeCrypto.OPENSSL_TO_STANDARD_CIPHER_SUITES.keySet());
        NativeCrypto.SSL_set_cipher_lists(s, ciphers.toArray(new String[ciphers.size()]));

        NativeCrypto.SSL_free(s);
        NativeCrypto.SSL_CTX_free(c);
    }
