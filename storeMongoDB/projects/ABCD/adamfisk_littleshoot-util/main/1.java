    public static String newUrl(final String baseUrl, 
        final Map<String, String> paramMap)
        {
        final StringBuilder sb = new StringBuilder();
        sb.append(baseUrl);
        sb.append(getUrlParameters(paramMap, true));
        return sb.toString();
        }
