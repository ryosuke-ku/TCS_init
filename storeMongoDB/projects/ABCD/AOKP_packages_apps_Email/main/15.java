    protected String getFirstHeader(String name) throws MessagingException {
        return getMimeHeaders().getFirstHeader(name);
    }
