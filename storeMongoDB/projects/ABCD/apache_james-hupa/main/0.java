    public static String replaceAll(String txt, Pattern pattern, String replacement) {
        return pattern.matcher(txt).replaceAll(replacement);
    }
