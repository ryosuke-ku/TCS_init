    public static Map<String, String> mapPathParams(final String requestPath, final String configPath) {
        final Map<Integer, String> pathElementsMap = extractPathSegments(requestPath);
        final Map<String, Integer> pathVariableNameMap = extractPathVariableNames(configPath);
        
        final Map<String, String> params = new HashMap<String, String>();
        final Matcher configMatcher = PLACEHOLDER_PATTERN.matcher(configPath);
        while (configMatcher.find()) {
            final String paramName = configMatcher.group(1);
            final String value = pathElementsMap.get(pathVariableNameMap.get(paramName));
            params.put(paramName, value);
        }
        return params;
    }
