    public static String crypt(final String key, final String salt) {
        return crypt(key.getBytes(Charsets.UTF_8), salt);
    }
