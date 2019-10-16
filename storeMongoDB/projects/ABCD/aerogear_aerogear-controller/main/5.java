    public static boolean acceptsMediaType(final Set<String> acceptHeaders, final Set<MediaType> produces) {
        if (acceptHeaders.isEmpty() || acceptHeaders.contains(MediaType.ANY)) {
            return true;
        }
        return getAcceptedMediaType(acceptHeaders, produces).isPresent();
    }
