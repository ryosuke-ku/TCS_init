    public String getExtendedHeader(String name) throws MessagingException {
        if (mExtendedHeader == null) {
            return null;
        }
        return mExtendedHeader.getFirstHeader(name);
    }
