    public void test_d2i_SSL_SESSION() throws Exception {
        try {
            NativeCrypto.d2i_SSL_SESSION(null);
            fail();
        } catch (NullPointerException expected) {
        }

        assertEquals(NULL, NativeCrypto.d2i_SSL_SESSION(new byte[0]));
        assertEquals(NULL, NativeCrypto.d2i_SSL_SESSION(new byte[1]));

        // positive testing by test_i2d_SSL_SESSION
    }
