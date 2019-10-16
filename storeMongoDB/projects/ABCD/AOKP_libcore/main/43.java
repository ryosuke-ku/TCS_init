    private static int X509_NAME_hash(X500Principal principal, String algorithm) {
        try {
            byte[] digest = MessageDigest.getInstance(algorithm).digest(principal.getEncoded());
            int offset = 0;
            return (((digest[offset++] & 0xff) <<  0) |
                    ((digest[offset++] & 0xff) <<  8) |
                    ((digest[offset++] & 0xff) << 16) |
                    ((digest[offset  ] & 0xff) << 24));
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }
