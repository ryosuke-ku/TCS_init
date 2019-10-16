    public static String injectParamValues(final String str, final Map<String, Object> map) {
        final Matcher matcher = PLACEHOLDER_PATTERN.matcher(str);
        final StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, String.valueOf(map.get(matcher.group(1))));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
