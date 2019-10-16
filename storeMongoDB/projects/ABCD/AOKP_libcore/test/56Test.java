    public void test_SSL_use_PrivateKey_for_tls_channel_id() throws Exception {
        initChannelIdKey();

        try {
            NativeCrypto.SSL_set1_tls_channel_id(NULL, NULL);
            fail();
        } catch (NullPointerException expected) {
        }

        long c = NativeCrypto.SSL_CTX_new();
        long s = NativeCrypto.SSL_new(c);

        try {
            NativeCrypto.SSL_set1_tls_channel_id(s, NULL);
            fail();
        } catch (NullPointerException expected) {
        }

        // Use the key natively. This works because the initChannelIdKey method ensures that the
        // key is backed by OpenSSL.
        NativeCrypto.SSL_set1_tls_channel_id(s, CHANNEL_ID_PRIVATE_KEY.getPkeyContext());

        NativeCrypto.SSL_free(s);
        NativeCrypto.SSL_CTX_free(c);
    }
