    public static boolean isFeed(String a) {
        try {
            MimeType mta = new MimeType(a.toLowerCase());
            MimeType mtb = new MimeType(Constants.ATOM_MEDIA_TYPE);
            MimeType mtc = new MimeType(Constants.FEED_MEDIA_TYPE);
            return isMatch(mta, mtc) || (isMatch(mta, mtb) && isMatchType(mta.getParameter("type"), "feed"));
        } catch (Exception e) {
        }
        return false;
    }
