    public static boolean isMimeType(String a) {
        boolean answer = false;
        try {
            new MimeType(a);
            answer = true;
        } catch (javax.activation.MimeTypeParseException e) {
            answer = false;
        }
        return answer;
    }
