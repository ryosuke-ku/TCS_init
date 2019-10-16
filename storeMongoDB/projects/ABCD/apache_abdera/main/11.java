    public static <T extends Base> String getMimeType(T base) {
        String type = null;
        if (base instanceof Document) {
            Document doc = (Document)base;
            MimeType mt = doc.getContentType();
            type = (mt != null) ? mt.toString() : getMimeType(doc.getRoot());
        } else if (base instanceof Element) {
            Element el = (Element)base;
            if (el.getDocument() != null) {
                MimeType mt = el.getDocument().getContentType();
                type = (mt != null) ? mt.toString() : null;
            }
            if (type == null) {
                if (el instanceof Feed)
                    type = Constants.FEED_MEDIA_TYPE;
                else if (el instanceof Entry)
                    type = Constants.ENTRY_MEDIA_TYPE;
                else if (el instanceof Service)
                    type = Constants.APP_MEDIA_TYPE;
                else if (el instanceof Categories)
                    type = Constants.CAT_MEDIA_TYPE;
            }
        }
        if (type == null)
            type = base.getFactory().getMimeType(base);
        return (type != null) ? type : Constants.XML_MEDIA_TYPE;
    }
