    public void setProxyUserAndPassword(String user, String password) {
        if (user == null) {
            remove(SessionParameter.PROXY_USER);
            remove(SessionParameter.PROXY_PASSWORD);
        } else {
            put(SessionParameter.PROXY_USER, user);
            put(SessionParameter.PROXY_PASSWORD, password);
        }
    }
