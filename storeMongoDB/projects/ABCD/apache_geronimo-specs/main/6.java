    public boolean match(String rawdata) throws MimeTypeParseException {
        return match(new MimeType(rawdata));
    }
