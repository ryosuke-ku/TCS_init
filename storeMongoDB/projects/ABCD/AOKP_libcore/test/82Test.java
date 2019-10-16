            public void afterHandshake(long session, long s, long c,
                                       Socket sock, FileDescriptor fd,
                                       SSLHandshakeCallbacks callback)
                    throws Exception {
                byte[] b = NativeCrypto.i2d_SSL_SESSION(session);
                assertNotNull(b);
                long session2 = NativeCrypto.d2i_SSL_SESSION(b);
                assertTrue(session2 != NULL);

                // Make sure d2i_SSL_SESSION retores SSL_SESSION_cipher value http://b/7091840
                assertTrue(NativeCrypto.SSL_SESSION_cipher(session2) != null);
                assertEquals(NativeCrypto.SSL_SESSION_cipher(session),
                             NativeCrypto.SSL_SESSION_cipher(session2));

                NativeCrypto.SSL_SESSION_free(session2);
                super.afterHandshake(session, s, c, sock, fd, callback);
            }
