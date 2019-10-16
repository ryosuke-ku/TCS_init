    public static Date toDate(int year, int month, int day, int hours, int minutes, int seconds) {
        final Calendar c = Calendar.getInstance();
        c.set(year, month, day, hours, minutes, seconds);
        return c.getTime();
    }
