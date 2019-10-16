    public void testGetShiftTimeDiff() {
        DateTimeComponent dateTimeComponent = new DateTimeComponent(
            new Date(SSDatePicker.DEFAULT_MINIMUM_YEAR - 1900, 0, 1),
            new Date(SSDatePicker.DEFAULT_MAXIMUM_YEAR - 1900, 11, 31),
            new Date(2013, 5, 15), 
            0, 
            86400000
        );
        
        Date startDate = new Date(2013, 5, 18);
        Date endDate = new Date(2013, 5, 18);
        endDate.setHours(2);
        endDate.setMinutes(15);
        String expected = "( 2.25h )";
        
        String result = dateTimeComponent.getShiftTimeDiff(startDate, endDate);
        assertEquals("The time diff return was not as expected", expected, result);
    }
