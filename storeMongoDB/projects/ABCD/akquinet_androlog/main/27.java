    public static int e(Object object, String msg, Throwable tr) {
        if (object != null) {
            return e(object.getClass().getName(), msg, tr);
        }
        return 0;
    }
