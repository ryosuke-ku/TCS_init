    public void testSetCriteriaObjectEmpty() {
        TextFilter textFilter = new TextFilter();
        
        TextFilter.TextFilterCriteria textFilterCriteria = new TextFilter.TextFilterCriteria();
        textFilterCriteria.setCriteria("Bla bla bla");
        textFilterCriteria.setFindEmptyEntriesOnly(true);
        
        textFilter.setCriteria(textFilterCriteria);
        
        textFilter.setCriteriaObjectEmpty();
        
        assertEquals("The criteria was expected to be empty", "", textFilter.getCriteria().getCriteria());
        assertFalse("The find empty entries only was expected to be false", textFilter.getCriteria().isFindEmptyEntriesOnly());
    }
