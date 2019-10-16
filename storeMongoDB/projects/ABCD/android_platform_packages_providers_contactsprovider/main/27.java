    protected static boolean isDirectoryParamValid(Uri uri) {
        final String directory = getQueryParameter(uri, ContactsContract.DIRECTORY_PARAM_KEY);
        if (directory == null) {
            return true;
        }
        try {
            Long.parseLong(directory);
            return true;
        } catch (NumberFormatException e) {
            Log.e(TAG, "Invalid directory ID: " + directory);
            // Return null cursor when invalid directory id is provided
            return false;
        }
    }
