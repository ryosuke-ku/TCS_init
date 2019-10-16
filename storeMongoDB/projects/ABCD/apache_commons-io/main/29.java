    private static int read(final InputStream input)
        throws IOException
    {
        final int value = input.read();

        if( EOF == value ) {
            throw new EOFException( "Unexpected EOF reached" );
        }

        return value;
    }
