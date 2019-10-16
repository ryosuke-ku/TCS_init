    public void testClearFields() {
        TextFilter textFilter = new TextFilter();
        
        textFilter.getCheckBox().setValue(true);
        textFilter.getTextBox().setText("This will be removed");
        
        textFilter.clearFields();
        
        assertFalse(
            "The value of the check box was expected to be false after the clearFields function was called",
            textFilter.getCheckBox().getValue()
        );
        assertEquals(
            "The text in the text box was expected to be an empty string after the clearFields function was called", 
            "", 
            textFilter.getTextBox().getValue()
        );
    }
