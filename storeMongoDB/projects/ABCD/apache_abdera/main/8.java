    public static boolean isXml(String a) {
        boolean answer = isMatch(Constants.XML_MEDIA_TYPE, a) || isMatch("text/xml", a);
        if (!answer) {
            try {
                MimeType mta = new MimeType(a);
                answer =
                    (("application".equalsIgnoreCase(mta.getPrimaryType()) || "text".equalsIgnoreCase(mta
                        .getPrimaryType())) && mta.getSubType().equals("xml") || mta.getSubType().endsWith("+xml"));
            } catch (Exception e) {
            }
        }
        return answer;
    }
