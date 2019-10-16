    private void testParseEmailDateTimeHelper(String date, int year, int month,
            int day, int hour, int minute, int second, int millis) throws Exception {
        GregorianCalendar cal = new GregorianCalendar(year, month - 1, day,
                hour, minute, second);
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));
        long timeInMillis = Utility.parseEmailDateTimeToMillis(date);
        assertEquals(cal.getTimeInMillis() + millis, timeInMillis);
    }
