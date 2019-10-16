    public static void writeSwappedDouble(final OutputStream output, final double value)
        throws IOException
    {
        writeSwappedLong( output, Double.doubleToLongBits( value ) );
    }
