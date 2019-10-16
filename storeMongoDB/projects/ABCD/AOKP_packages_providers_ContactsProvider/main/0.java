    public T match(Uri uri) {
        int match = mUriMatcher.match(uri);
        if (match == UriMatcher.NO_MATCH) {
            return mNoMatchUriType;
        }
        return mValues[match];
    }
