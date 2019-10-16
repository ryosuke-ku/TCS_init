    public static Map<Integer, String> extractPathSegments(final String path) {
        final Matcher requestMatcher = PATH_PLACEHOLDER_PATTERN.matcher(path);
        final Map<Integer, String> map = new HashMap<Integer, String>();
        for (int i = 0; requestMatcher.find(); i++) {
            map.put(i, requestMatcher.group(1));
        }
        return map;
    }
