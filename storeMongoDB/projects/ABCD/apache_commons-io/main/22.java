    public static long readSwappedUnsignedInteger(final InputStream input)
        throws IOException
    {
        final int value1 = read( input );
        final int value2 = read( input );
        final int value3 = read( input );
        final int value4 = read( input );

        final long low = ( ( ( value1 & 0xff ) << 0 ) +
                     ( ( value2 & 0xff ) << 8 ) +
                     ( ( value3 & 0xff ) << 16 ) );

        final long high = value4 & 0xff;

        return (high << 24) + (0xffffffffL & low); 
    }
