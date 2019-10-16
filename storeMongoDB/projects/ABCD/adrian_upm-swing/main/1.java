    public static void checkNotNull(Object toCheck, String errorMessage) {
        checkArgument(null != toCheck, errorMessage);
        // After upgrade to >JRE 1.4, use generics to return the object
    }
