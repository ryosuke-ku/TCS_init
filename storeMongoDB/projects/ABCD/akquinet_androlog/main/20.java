    public static int w(String tag, Throwable tr) {
        collectLogEntry(Constants.WARN, tag, "", null);
        if (isLoggable(tag, Constants.WARN)) {
            return android.util.Log.w(tag, tr);
        }
        return 0;
    }
