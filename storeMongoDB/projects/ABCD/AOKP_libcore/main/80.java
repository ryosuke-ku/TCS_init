    public static native void SSL_write(long sslNativePointer,
                                        FileDescriptor fd,
                                        SSLHandshakeCallbacks shc,
                                        byte[] b, int off, int len, int writeTimeoutMillis)
        throws IOException;
