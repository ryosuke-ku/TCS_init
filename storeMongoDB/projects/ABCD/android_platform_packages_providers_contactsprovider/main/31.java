    public static Uri convertToLocalUri(Uri uri, Uri initialUri) {
        final String filterParam =
                uri.getPathSegments().size() > initialUri.getPathSegments().size()
                        ? uri.getLastPathSegment()
                        : "";
        final Uri.Builder builder = initialUri.buildUpon().appendPath(filterParam);
        addQueryParametersFromUri(builder, uri, MODIFIED_KEY_SET_FOR_ENTERPRISE_FILTER);
        final String directory = getQueryParameter(uri, ContactsContract.DIRECTORY_PARAM_KEY);
        if (!TextUtils.isEmpty(directory)) {
            final long directoryId = Long.parseLong(directory);
            if (Directory.isEnterpriseDirectoryId(directoryId)) {
                builder.appendQueryParameter(ContactsContract.DIRECTORY_PARAM_KEY,
                        String.valueOf(directoryId - Directory.ENTERPRISE_DIRECTORY_ID_BASE));
            } else {
                builder.appendQueryParameter(
                        ContactsContract.DIRECTORY_PARAM_KEY,
                        String.valueOf(directoryId));
            }
        }
        return builder.build();
    }
