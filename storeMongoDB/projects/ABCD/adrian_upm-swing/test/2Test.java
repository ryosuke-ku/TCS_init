    private File createVer110Db() throws IOException, CryptoException {
        byte[] b = createVer110DbBytes();
        return saveToFile(b);
    }
