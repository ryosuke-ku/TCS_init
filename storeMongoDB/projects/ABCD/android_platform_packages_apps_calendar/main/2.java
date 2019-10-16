    protected long constructDefaultStartTime(long now) {
        Time defaultStart = new Time();
        defaultStart.set(now);
        defaultStart.second = 0;
        defaultStart.minute = 30;
        long defaultStartMillis = defaultStart.toMillis(false);
        if (now < defaultStartMillis) {
            return defaultStartMillis;
        } else {
            return defaultStartMillis + 30 * DateUtils.MINUTE_IN_MILLIS;
        }
    }
