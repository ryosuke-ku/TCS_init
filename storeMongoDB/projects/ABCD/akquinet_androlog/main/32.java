    public static boolean isLoggable(Object object, int level) {
        return isLoggable(object.getClass().getName(), level);
    }
