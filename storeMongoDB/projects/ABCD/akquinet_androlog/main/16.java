    public static int i(Object object, String msg, Throwable tr) {
        if (object != null) {
            return i(object.getClass().getName(), msg, tr);
        }
        return 0;
    }
