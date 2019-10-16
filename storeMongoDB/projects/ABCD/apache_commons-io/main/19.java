    public static int readSwappedUnsignedShort(final InputStream input)
        throws IOException
    {
        final int value1 = read( input );
        final int value2 = read( input );

        return ( ( ( value1 & 0xff ) << 0 ) +
            ( ( value2 & 0xff ) << 8 ) );
    }
