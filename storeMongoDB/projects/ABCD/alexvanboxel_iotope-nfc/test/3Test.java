    public void bin2hex() throws IOException {
        byte[] dataCompare = new byte[] { (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0x07, (byte) 0x80, (byte) 0x69, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF };
        
        String dataAsText = Resources.toString(getClass().getResource("bin2hex.txt"), Charset.forName("utf8"));
        byte[] dataAsBinary = IOUtil.bin2hex(dataAsText);
        
        Assert.assertEquals(IOUtil.hex(dataCompare), IOUtil.hex(dataAsBinary));
    }
