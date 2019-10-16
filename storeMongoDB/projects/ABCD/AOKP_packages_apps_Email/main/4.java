    public static String foldAndEncode2(String s, int usedCharacters) {
        // james.mime4j.codec.EncoderUtil.java
        // encode:  encodeIfNecessary(text, usage, numUsedInHeaderName)
        // Usage.TEXT_TOKENlooks like the right thing for subjects
        // use WORD_ENTITY for address/names

        String encoded = EncoderUtil.encodeIfNecessary(s, EncoderUtil.Usage.TEXT_TOKEN,
                usedCharacters);

        return fold(encoded, usedCharacters);
    }
