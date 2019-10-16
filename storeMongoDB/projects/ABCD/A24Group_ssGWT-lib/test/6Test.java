    public void testCheckFilterActive() {
        TextFilter textFilter = new TextFilter();
        
        // Case 1
        TextFilter.TextFilterCriteria textFilterCriteria = new TextFilter.TextFilterCriteria();
        textFilterCriteria.setCriteria("Some text here");
        textFilterCriteria.setFindEmptyEntriesOnly(false);
        
        textFilter.setCriteria(textFilterCriteria);
        
        assertTrue(
            "The checkFilterActive function was expected to return true for the following criteria " +
            "object { \"criteria\" : \"Some text here\", \"findEmptyEntriesOnly\" : false }", 
            textFilter.checkFilterActive()
        );
        
        // Case 2
        textFilterCriteria.setCriteria("");
        textFilterCriteria.setFindEmptyEntriesOnly(true);
        
        textFilter.setCriteria(textFilterCriteria);
        
        assertTrue(
            "The checkFilterActive function was expected to return true for the following criteria " +
            "object { \"criteria\" : \"\", \"findEmptyEntriesOnly\" : true }", 
            textFilter.checkFilterActive()
        );
        
        // Case 3
        textFilterCriteria.setCriteria("");
        textFilterCriteria.setFindEmptyEntriesOnly(false);
        
        textFilter.setCriteria(textFilterCriteria);
        
        assertFalse(
            "The checkFilterActive function was expected to return false for the following criteria " +
            "object { \"criteria\" : \"\", \"findEmptyEntriesOnly\" : false }", 
            textFilter.checkFilterActive()
        );
    }
