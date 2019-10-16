    public static Part findPartByContentId(Part part, String contentId) throws Exception {
        if (part.getBody() instanceof Multipart) {
            Multipart multipart = (Multipart)part.getBody();
            for (int i = 0, count = multipart.getCount(); i < count; i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                Part ret = findPartByContentId(bodyPart, contentId);
                if (ret != null) {
                    return ret;
                }
            }
        }
        String cid = part.getContentId();
        if (contentId.equals(cid)) {
            return part;
        }
        return null;
    }
