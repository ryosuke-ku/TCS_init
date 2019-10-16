    public String toString() {
        StringBuilder out = new StringBuilder("[OperationScheduler:");
        for (String key : new TreeSet<String>(mStorage.getAll().keySet())) {  // Sort keys
            if (key.startsWith(PREFIX)) {
                if (key.endsWith("TimeMillis")) {
                    Time time = new Time();
                    time.set(mStorage.getLong(key, 0));
                    out.append(" ").append(key.substring(PREFIX.length(), key.length() - 10));
                    out.append("=").append(time.format("%Y-%m-%d/%H:%M:%S"));
                } else {
                    out.append(" ").append(key.substring(PREFIX.length()));
                    out.append("=").append(mStorage.getAll().get(key).toString());
                }
            }
        }
        return out.append("]").toString();
    }
