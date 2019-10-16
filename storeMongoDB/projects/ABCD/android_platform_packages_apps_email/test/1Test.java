    private void testParseDateTimesHelper(String date, int year, int month,
            int day, int hour, int minute, int second) throws Exception {
        GregorianCalendar cal = Utility.parseDateTimeToCalendar(date);
        assertEquals(year, cal.get(Calendar.YEAR));
        assertEquals(month, cal.get(Calendar.MONTH) + 1);
        assertEquals(day, cal.get(Calendar.DAY_OF_MONTH));
        assertEquals(hour, cal.get(Calendar.HOUR_OF_DAY));
        assertEquals(minute, cal.get(Calendar.MINUTE));
        assertEquals(second, cal.get(Calendar.SECOND));
    }
