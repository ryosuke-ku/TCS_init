    public void testsetStartDate() {
        DateTimeComponent dateTimeComponent = new DateTimeComponent(
            new Date(SSDatePicker.DEFAULT_MINIMUM_YEAR - 1900, 0, 1),
            new Date(SSDatePicker.DEFAULT_MAXIMUM_YEAR - 1900, 11, 31),
            new Date(2013, 5, 15), 
            0, 
            86400000
        );
        
        dateTimeComponent.setStartDate(new Date(2013, 5, 15));
        assertEquals("The start Date was not as expected",  new Date(2013, 5, 15), dateTimeComponent.getStartDate());
        assertEquals("The end Date was not as expected", new Date(2013, 5, 15), dateTimeComponent.getEndDate());
    }
