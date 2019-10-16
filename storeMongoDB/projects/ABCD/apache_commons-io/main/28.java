    public static double readSwappedDouble(final InputStream input)
        throws IOException
    {
        return Double.longBitsToDouble( readSwappedLong( input ) );
    }
