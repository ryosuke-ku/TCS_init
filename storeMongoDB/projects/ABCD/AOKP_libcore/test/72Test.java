    public void test_SSL_set_tlsext_host_name() throws Exception {
        // NULL SSL
        try {
            NativeCrypto.SSL_set_tlsext_host_name(NULL, null);
            fail();
        } catch (NullPointerException expected) {
        }

        final String hostname = "www.android.com";

        {
            long c = NativeCrypto.SSL_CTX_new();
            long s = NativeCrypto.SSL_new(c);

            // null hostname
            try {
                NativeCrypto.SSL_set_tlsext_host_name(s, null);
                fail();
            } catch (NullPointerException expected) {
            }

            // too long hostname
            try {
                char[] longHostname = new char[256];
                Arrays.fill(longHostname, 'w');
                NativeCrypto.SSL_set_tlsext_host_name(s, new String(longHostname));
                fail();
            } catch (SSLException expected) {
            }

            assertNull(NativeCrypto.SSL_get_servername(s));
            NativeCrypto.SSL_set_tlsext_host_name(s, new String(hostname));
            assertEquals(hostname, NativeCrypto.SSL_get_servername(s));

            NativeCrypto.SSL_free(s);
            NativeCrypto.SSL_CTX_free(c);
        }

        final ServerSocket listener = new ServerSocket(0);

        // normal
        Hooks cHooks = new Hooks() {
            @Override
            public long beforeHandshake(long c) throws SSLException {
                long s = super.beforeHandshake(c);
                NativeCrypto.SSL_set_tlsext_host_name(s, hostname);
                return s;
            }
        };
        Hooks sHooks = new ServerHooks(getServerPrivateKey(), getServerCertificates()) {
            @Override
            public void afterHandshake(long session, long s, long c,
                                       Socket sock, FileDescriptor fd,
                                       SSLHandshakeCallbacks callback)
                    throws Exception {
                assertEquals(hostname, NativeCrypto.SSL_get_servername(s));
                super.afterHandshake(session, s, c, sock, fd, callback);
            }
        };
        Future<TestSSLHandshakeCallbacks> client = handshake(listener, 0, true, cHooks, null, null);
        Future<TestSSLHandshakeCallbacks> server = handshake(listener, 0, false, sHooks, null, null);
        client.get(TIMEOUT_SECONDS, TimeUnit.SECONDS);
        server.get(TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }
