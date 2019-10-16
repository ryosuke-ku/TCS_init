    public void setExtendedHeaders(String headers) throws MessagingException {
        if (TextUtils.isEmpty(headers)) {
            mExtendedHeader = null;
        } else {
            mExtendedHeader = new MimeHeader();
            for (String header : END_OF_LINE.split(headers)) {
                String[] tokens = header.split(":", 2);
                if (tokens.length != 2) {
                    throw new MessagingException("Illegal extended headers: " + headers);
                }
                mExtendedHeader.setHeader(tokens[0].trim(), tokens[1].trim());
            }
        }
    }
