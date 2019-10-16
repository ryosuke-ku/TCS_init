    public static int readSwappedInteger(final InputStream input)
        throws IOException
    {
        final int value1 = read( input );
        final int value2 = read( input );
        final int value3 = read( input );
        final int value4 = read( input );

        return ( ( value1 & 0xff ) << 0 ) +
            ( ( value2 & 0xff ) << 8 ) +
            ( ( value3 & 0xff ) << 16 ) +
            ( ( value4 & 0xff ) << 24 );
    }
