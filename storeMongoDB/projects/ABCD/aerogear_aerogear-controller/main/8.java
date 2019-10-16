    public static boolean segmentsMatch(final String placeHolderPath, final String realPath) {
        final List<String> placeholderSegments = pathSegements(placeHolderPath);
        final List<String> realPathSegments = pathSegements(realPath);
        if (placeholderSegments.size() != realPathSegments.size()) {
            return false;
        }
        final int segments = placeholderSegments.size();
        for (int i = 0; i < segments; i++ ) {
            final String placeholderSegment = placeholderSegments.get(i);
            final String realPathSegment = realPathSegments.get(i);
            if (placeholderSegment.charAt(0) == '{') {
                if (realPathSegment == null || realPathSegment.equals("")) {
                    return false;
                }
            } else {
                if (!placeholderSegment.equals(realPathSegment)) {
                    return false;
                }
            }
        }
        return true;
    }
