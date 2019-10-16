    public void testGetRestDate() {
        Date defaultSelectedDate = new Date(2014, 6, 15);
        defaultSelectedDate.setHours(12);
        defaultSelectedDate.setMinutes(30);
        
        DateTimeComponent dateTimeComponent = new DateTimeComponent(
            new Date(SSDatePicker.DEFAULT_MINIMUM_YEAR - 1900, 0, 1),
            new Date(SSDatePicker.DEFAULT_MAXIMUM_YEAR - 1900, 11, 31),
            defaultSelectedDate, 
            0, 
            86400000
        );
        
        Date restedDate = dateTimeComponent.getRestDate(new Date(2013, 5, 15));
        
        Date expectedDate = new Date(2013, 5, 15);
        expectedDate.setHours(12);
        expectedDate.setMinutes(30);
        
        assertEquals("The reset Date was not as expected", expectedDate, restedDate);
    }
