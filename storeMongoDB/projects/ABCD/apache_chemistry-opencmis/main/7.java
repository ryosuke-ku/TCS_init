    public static byte[] decode(String s, int options) throws java.io.IOException {

        if (s == null) {
            throw new NullPointerException("Input string was null.");
        } // end if

        byte[] bytes;
        try {
            bytes = s.getBytes(PREFERRED_ENCODING);
        } // end try
        catch (java.io.UnsupportedEncodingException uee) {
            bytes = IOUtils.toUTF8Bytes(s);
        } // end catch
          // </change>

        // Decode
        bytes = decode(bytes, 0, bytes.length, options);

        // Check to see if it's gzip-compressed
        // GZIP Magic Two-Byte Number: 0x8b1f (35615)
        boolean dontGunzip = (options & DONT_GUNZIP) != 0;
        if ((bytes != null) && (bytes.length >= 4) && (!dontGunzip)) {

            int head = ((int) bytes[0] & 0xff) | ((bytes[1] << 8) & 0xff00);
            if (java.util.zip.GZIPInputStream.GZIP_MAGIC == head) {
                java.io.ByteArrayInputStream bais = null;
                java.util.zip.GZIPInputStream gzis = null;
                java.io.ByteArrayOutputStream baos = null;
                byte[] buffer = new byte[2048];
                int length = 0;

                try {
                    baos = new java.io.ByteArrayOutputStream();
                    bais = new java.io.ByteArrayInputStream(bytes);
                    gzis = new java.util.zip.GZIPInputStream(bais, 64 * 1024);

                    while ((length = gzis.read(buffer)) >= 0) {
                        baos.write(buffer, 0, length);
                    } // end while: reading input

                    // No error? Get new bytes.
                    bytes = baos.toByteArray();

                } // end try
                catch (java.io.IOException e) {
                    LOG.error(e.getMessage(), e);
                    // Just return originally-decoded bytes
                } // end catch
                finally {
                    IOUtils.closeQuietly(baos);
                    IOUtils.closeQuietly(gzis);
                    IOUtils.closeQuietly(bais);
                } // end finally

            } // end if: gzipped
        } // end if: bytes.length >= 2

        return bytes;
    } // end decode
