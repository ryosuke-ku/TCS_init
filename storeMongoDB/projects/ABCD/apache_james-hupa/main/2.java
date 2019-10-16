    static public List<BodyPart> extractMessageAttachments(Log logger, Object content) throws MessagingException, IOException {
        ArrayList<BodyPart> ret = new ArrayList<BodyPart>();
        if (content instanceof Multipart) {
            Multipart part = (Multipart) content;
            for (int i = 0; i < part.getCount(); i++) {
                BodyPart bodyPart = part.getBodyPart(i);
                String fileName = bodyPart.getFileName();
                String[] contentId = bodyPart.getHeader("Content-ID");
                if (bodyPart.isMimeType("multipart/*")) {
                    ret.addAll(extractMessageAttachments(logger, bodyPart.getContent()));
                } else {
                    if (contentId != null || fileName != null) {
                        ret.add(bodyPart);
                    }
                }
            }
        } else {
            logger.error("Unknown content: " + content.getClass().getName());
        }
        return ret;
    }
