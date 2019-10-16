    public void testClearFields() {
        DateFilter dateFilter = new DateFilter("someStyleName");
        
        dateFilter.getCheckBox().setValue(true);
        dateFilter.getFilterList().setSelectedIndex(5);
        Date date = new Date();
        dateFilter.getFromDateBox().setValue(date);
        dateFilter.getToDateBox().setValue(date);
        
        dateFilter.clearFields();
        
        assertFalse(
            "The value of the checkbox was expected to be false after the clearFields function was called",
            dateFilter.getCheckBox().getValue()
        );
        assertEquals(
            "The filter list's selected index was expected to be 0 after the clearFields function was called", 
            0,
            dateFilter.getFilterList().getSelectedIndex()
        );
        assertNull(
            "The date from datebox was expected to be empty after the clearFields function was called", 
            dateFilter.getFromDateBox().getValue()
        );
        assertNull(
            "The date to datebox was expected to be empty after the clearFields function was called", 
            dateFilter.getToDateBox().getValue()
        );
    }
