    private static void check(boolean requirements, String error) {
        String message = (error == null || error.trim().length() <= 0) ? DEFAULT_MESSAGE : error;
        if (!requirements) {
            throw new IllegalArgumentException(message);
        }
    }
