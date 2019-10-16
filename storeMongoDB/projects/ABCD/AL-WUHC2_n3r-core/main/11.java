    public static Date parse(String strDate) {
        DateTime dt = new DateTime(strDate);
        Calendar cal = Calendar.getInstance();
        cal.clear();

        Integer year = dt.getYear();
        Integer month = dt.getMonth();
        Integer day = dt.getDay();
        //coerce missing times to 0:
        Integer hour = dt.getHour() == null ? 0 : dt.getHour();
        Integer minute = dt.getMinute() == null ? 0 : dt.getMinute();
        Integer second = dt.getSecond() == null ? 0 : dt.getSecond();
        Integer nanos = dt.getNanoseconds() == null ? 0 : dt.getNanoseconds();

        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1); // 0-based
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, hour); // 0..23
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        cal.set(Calendar.MILLISECOND, nanos / 1000000);

        return cal.getTime();
    }
