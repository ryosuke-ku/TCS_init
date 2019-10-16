    protected static final Uri.Builder addQueryParametersFromUri(Uri.Builder builder, Uri uri,
            Set<String> ignoredKeys) {
        Set<String> keys = uri.getQueryParameterNames();

        for (String key : keys) {
            if(ignoredKeys == null || !ignoredKeys.contains(key)) {
                builder.appendQueryParameter(key, getQueryParameter(uri, key));
            }
        }

        return builder;
    }
