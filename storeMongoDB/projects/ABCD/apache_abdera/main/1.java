    public void addType(String... mediaRanges) {
        mediaRanges = MimeTypeHelper.condense(mediaRanges);
        for (String mediaRange : mediaRanges) {
            try {
                addSimpleExtension(FeaturesHelper.TYPE, new MimeType(mediaRange).toString());
            } catch (MimeTypeParseException e) {
            }
        }
    }
