    public static native void SSL_shutdown(long sslNativePointer,
                                           FileDescriptor fd,
                                           SSLHandshakeCallbacks shc) throws IOException;
