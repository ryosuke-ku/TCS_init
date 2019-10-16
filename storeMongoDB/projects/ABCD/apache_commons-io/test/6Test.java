    private void readBOMInputStreamTwice(final String resource) throws Exception {
        final InputStream inputStream = this.getClass().getResourceAsStream(resource);
        Assert.assertNotNull(inputStream);
        final BOMInputStream bomInputStream = new BOMInputStream(inputStream);
        bomInputStream.mark(1000000);

        this.readFile(bomInputStream);
        bomInputStream.reset();
        this.readFile(bomInputStream);
        inputStream.close();
        bomInputStream.close();
    }
