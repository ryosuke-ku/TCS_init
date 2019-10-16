    public byte[] digest(final InputStream data) throws IOException {
        return updateDigest(messageDigest, data).digest();
    }
