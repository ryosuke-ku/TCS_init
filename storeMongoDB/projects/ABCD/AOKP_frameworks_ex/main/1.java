    private void check(boolean condition, String exception) {
        if (!condition) {
            throw new IllegalStateException(exception);
        }
    }
