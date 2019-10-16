    public void setExtendedHeader(String name, String value) throws MessagingException {
        if (value == null) {
            if (mExtendedHeader != null) {
                mExtendedHeader.removeHeader(name);
            }
            return;
        }
        if (mExtendedHeader == null) {
            mExtendedHeader = new MimeHeader();
        }
        mExtendedHeader.setHeader(name, END_OF_LINE.matcher(value).replaceAll(""));
    }
