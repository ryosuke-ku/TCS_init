    public static String replaceAllRecursive(String txt, Pattern pattern, String replacement) {
        while (pattern.matcher(txt).find())
            txt = pattern.matcher(txt).replaceAll(replacement);
        return txt;
    }
