    public static long readSwappedLong(final InputStream input)
        throws IOException
    {
        final byte[] bytes = new byte[8];
        for ( int i=0; i<8; i++ ) {
            bytes[i] = (byte) read( input );
        }
        return readSwappedLong( bytes, 0 );
    }
