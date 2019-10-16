    public static Future<TestSSLHandshakeCallbacks> handshake(final ServerSocket listener,
            final int timeout, final boolean client, final Hooks hooks, final byte[] npnProtocols,
            final byte[] alpnProtocols) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<TestSSLHandshakeCallbacks> future = executor.submit(
                new Callable<TestSSLHandshakeCallbacks>() {
            @Override public TestSSLHandshakeCallbacks call() throws Exception {
                Socket socket = (client
                                 ? new Socket(listener.getInetAddress(),
                                              listener.getLocalPort())
                                 : listener.accept());
                if (timeout == -1) {
                    return new TestSSLHandshakeCallbacks(socket, 0, null);
                }
                FileDescriptor fd = socket.getFileDescriptor$();
                long c = hooks.getContext();
                long s = hooks.beforeHandshake(c);
                TestSSLHandshakeCallbacks callback
                        = new TestSSLHandshakeCallbacks(socket, s, hooks);
                if (DEBUG) {
                    System.out.println("ssl=0x" + Long.toString(s, 16)
                                       + " handshake"
                                       + " context=0x" + Long.toString(c, 16)
                                       + " socket=" + socket
                                       + " fd=" + fd
                                       + " timeout=" + timeout
                                       + " client=" + client);
                }
                long session = NULL;
                try {
                    session = NativeCrypto.SSL_do_handshake(s, fd, callback, timeout, client,
                                                            npnProtocols, alpnProtocols);
                    if (DEBUG) {
                        System.out.println("ssl=0x" + Long.toString(s, 16)
                                           + " handshake"
                                           + " session=0x" + Long.toString(session, 16));
                    }
                } finally {
                    // Ensure afterHandshake is called to free resources
                    hooks.afterHandshake(session, s, c, socket, fd, callback);
                }
                return callback;
            }
        });
        executor.shutdown();
        return future;
    }
