    public static void checkValidOAuthCallback(String url, String errorMsg) {
        checkEmptyString(url, errorMsg);
        if (url.toLowerCase(ENGLISH).compareToIgnoreCase(AgoravaConstants.OUT_OF_BAND) != 0) {
            check(isUrl(url), errorMsg);
        }
    }
