    public static short readSwappedShort(final InputStream input)
        throws IOException
    {
        return (short)( ( ( read( input ) & 0xff ) << 0 ) +
            ( ( read( input ) & 0xff ) << 8 ) );
    }
