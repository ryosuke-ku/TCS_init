    public static int v(Object object, String msg, Throwable tr) {
        if (object != null) {
            return v(object.getClass().getName(), msg, tr);
        }
        return 0;
    }
