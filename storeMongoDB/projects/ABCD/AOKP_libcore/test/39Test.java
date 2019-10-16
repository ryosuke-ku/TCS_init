    public void test_EVP_CipherInit_ex_Success() throws Exception {
        final long ctx = NativeCrypto.EVP_CIPHER_CTX_new();
        try {
            final long evpCipher = NativeCrypto.EVP_get_cipherbyname("aes-128-ecb");
            NativeCrypto.EVP_CipherInit_ex(ctx, evpCipher, AES_128_KEY, null, true);
        } finally {
            NativeCrypto.EVP_CIPHER_CTX_cleanup(ctx);
        }
    }
