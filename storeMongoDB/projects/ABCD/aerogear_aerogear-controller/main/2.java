    public static Set<String> extractAcceptHeader(final HttpServletRequest request) {
        final String acceptHeader = request.getHeader("Accept");
        if (acceptHeader == null) {
            return Collections.emptySet();
        }
        final Set<String> acceptHeaders = new LinkedHashSet<String>();
        final Matcher m = ACCEPT_HEADER_PATTERN.matcher(acceptHeader);
        while (m.find()) {
            acceptHeaders.add(m.group(1));
        }
        return acceptHeaders;
    }
