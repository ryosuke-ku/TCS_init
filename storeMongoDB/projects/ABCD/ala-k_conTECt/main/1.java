    public void setEmail(final Email _email) {
        email = _email;
        if ( ! emails.contains(_email) ) {
            emails.add(_email);
        }
    }
