    public static native int SSL_do_handshake(long sslNativePointer,
                                              FileDescriptor fd,
                                              SSLHandshakeCallbacks shc,
                                              int timeoutMillis,
                                              boolean client_mode,
                                              byte[] npnProtocols,
                                              byte[] alpnProtocols)
        throws SSLException, SocketTimeoutException, CertificateException;
