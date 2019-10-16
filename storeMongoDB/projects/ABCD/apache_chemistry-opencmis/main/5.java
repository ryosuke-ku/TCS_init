    public static String encodeBytes(byte[] source, int off, int len, int options) throws java.io.IOException {
        byte[] encoded = encodeBytesToBytes(source, off, len, options);

        // Return value according to relevant encoding.
        try {
            return new String(encoded, PREFERRED_ENCODING);
        } // end try
        catch (java.io.UnsupportedEncodingException uue) {
            return IOUtils.toUTF8String(encoded);
        } // end catch

    } // end encodeBytes
