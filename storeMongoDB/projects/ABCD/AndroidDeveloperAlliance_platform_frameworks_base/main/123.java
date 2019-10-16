    public static String decode(String s) {
        if (s == null) {
            return null;
        }
        return UriCodec.decode(s, false, Charsets.UTF_8, false);
    }
