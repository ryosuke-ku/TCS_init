    public static String toString(String... tags) {
        StringBuilder buf = new StringBuilder();
        for (String tag : tags) {
            if (buf.length() > 0)
                buf.append(", ");
            EntityTag etag = new EntityTag(tag);
            buf.append(etag.toString());
        }
        return buf.toString();
    }
