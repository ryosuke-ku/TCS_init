    static public List<BodyPart> extractInlineImages(Log logger, Object content) throws MessagingException, IOException {
        ArrayList<BodyPart> ret = new ArrayList<BodyPart>();
        for (BodyPart attach : extractMessageAttachments(logger, content)) {
            if (attach.getHeader("Content-ID") != null && attach.getContentType().startsWith("image/"))
                ret.add(attach);
        }
        return ret;
    }
