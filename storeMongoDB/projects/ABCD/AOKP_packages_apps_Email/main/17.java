    public String[] getHeader(String name) throws MessagingException {
        return getMimeHeaders().getHeader(name);
    }
