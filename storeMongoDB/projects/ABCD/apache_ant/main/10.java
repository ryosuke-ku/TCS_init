    public void log(final Target target, final String message, final Throwable throwable,
            final int msgLevel) {
        fireMessageLogged(target, message, throwable, msgLevel);
    }
