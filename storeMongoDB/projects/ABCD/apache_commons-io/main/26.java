    public static float readSwappedFloat(final InputStream input)
        throws IOException
    {
        return Float.intBitsToFloat( readSwappedInteger( input ) );
    }
