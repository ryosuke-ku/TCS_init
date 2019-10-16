    public void testUpdateCriteriaObject() {
        TextFilter textFilter = new TextFilter();
        
        textFilter.getTextBox().setValue("Bla bla bla");
        textFilter.getCheckBox().setValue(true);
        
        textFilter.updateCriteriaObject();
        
        assertEquals("The criteria was expected to be empty", "Bla bla bla", textFilter.getCriteria().getCriteria());
        assertTrue("The find empty entries only was expected to be true", textFilter.getCriteria().isFindEmptyEntriesOnly());
    }
