    public void testBase64Stream() throws Exception {
        ByteArrayInputStream stream = new ByteArrayInputStream("dGVzdA==".getBytes("US-ASCII"));
        InputStream base64stream = new Base64.InputStream(stream);

        ByteArrayOutputStream output = new ByteArrayOutputStream();

        IOUtils.copy(base64stream, output);

        base64stream.close();

        assertArrayEquals("test".getBytes("US-ASCII"), output.toByteArray());
    }
