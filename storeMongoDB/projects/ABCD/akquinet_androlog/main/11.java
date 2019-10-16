    public static int d(Object object, String msg, Throwable tr) {
        if (object != null) {
            return d(object.getClass().getName(), msg, tr);
        }
        return 0;
    }
