    public void test1() {
        Date date = RDate.parse("2012-08-17");
        int day = RDate.getDay(date);
        assertEquals(17, day);

        assertEquals(31, RDate.getLastDayOfMonth(date));

        date = RDate.parse("2012-08-31");
        Date nextMonth = RDate.addMonths(date, 1);
        assertEquals("2012-09-30", RDate.toDateStr(nextMonth));
    }
