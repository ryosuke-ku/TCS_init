    public static void checkValidUrl(String url, String errorMsg) {
        checkEmptyString(url, errorMsg);
        check(isUrl(url), errorMsg);
    }
