    public static String decode(String s) {
        if (s == null) {
            return null;
        }
        return DecoderUtil.decodeEncodedWords(s);
    }
