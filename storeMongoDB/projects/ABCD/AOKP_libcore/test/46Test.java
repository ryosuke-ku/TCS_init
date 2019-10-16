    public void test_create_BIO_InputStream() throws Exception {
        byte[] actual = "Test".getBytes();
        ByteArrayInputStream is = new ByteArrayInputStream(actual);

        long ctx = NativeCrypto.create_BIO_InputStream(new OpenSSLBIOInputStream(is));
        try {
            byte[] buffer = new byte[1024];
            int numRead = NativeCrypto.BIO_read(ctx, buffer);
            assertEquals(actual.length, numRead);
            assertEquals(Arrays.toString(actual),
                    Arrays.toString(Arrays.copyOfRange(buffer, 0, numRead)));
        } finally {
            NativeCrypto.BIO_free(ctx);
        }

    }
