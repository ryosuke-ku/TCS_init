    public static native int SSL_read(long sslNativePointer,
                                      FileDescriptor fd,
                                      SSLHandshakeCallbacks shc,
                                      byte[] b, int off, int len, int readTimeoutMillis)
        throws IOException;
