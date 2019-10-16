    public static String encodeURL(String s) {
        if (s == null) {
            return null;
        }

        try {
            return URLEncoder.encode(s, UTF8);
        } catch (UnsupportedEncodingException e) {
            throw new CmisRuntimeException("Unsupported encoding 'UTF-8'!", e);
        }
    }
