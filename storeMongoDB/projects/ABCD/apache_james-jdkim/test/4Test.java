    public void testChunksAndPassthrough() throws NoSuchAlgorithmException,
            IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DigestOutputStream dos = new DigestOutputStream(MessageDigest
                .getInstance("sha-256"), bos);
        chunker(testData, dos);
        byte[] digest = dos.getDigest();
        assertTrue(Arrays.equals(expectedDigest, digest));
        assertTrue(Arrays.equals(testData, bos.toByteArray()));
    }
