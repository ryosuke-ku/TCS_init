    public static Set<String> extractPlaceHolders(final String str) {
        final Matcher matcher = PLACEHOLDER_PATTERN.matcher(str);
        final Set<String> params = new HashSet<String>();
        while (matcher.find()) {
            params.add(matcher.group(1));
        }
        return params;
    }
