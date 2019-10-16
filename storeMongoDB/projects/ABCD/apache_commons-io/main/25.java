    public static void writeSwappedFloat(final OutputStream output, final float value)
        throws IOException
    {
        writeSwappedInteger( output, Float.floatToIntBits( value ) );
    }
