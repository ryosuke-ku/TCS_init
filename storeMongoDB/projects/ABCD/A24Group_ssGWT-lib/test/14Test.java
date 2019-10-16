    public void testSetDatesCustom() {
        DateFilter dateFilter = new DateFilter("someStyleName");
        
        Date currentDate = new Date();
        currentDate = getDateWithoutMilliseconds(currentDate);
        String range = "Customised date range";
        
        dateFilter.setDates(currentDate, range);
        
        assertNull(
            "The From date was expected to be null, because the range was custom", 
            dateFilter.getFromDateBox().getValue()
        );
        assertNull(
            "The To date was expected to be null, because the range was custom",
            dateFilter.getToDateBox().getValue()
        );
    }
