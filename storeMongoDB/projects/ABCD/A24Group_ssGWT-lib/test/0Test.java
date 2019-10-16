    public void testDateToMilliseconds() {
        long value = DateTimeComponent.dateToMilliseconds(4, 5, 60, 30);
        assertEquals("The value returned by the function was not as expected ", value, 367230000);
    }
