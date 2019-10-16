    public void testSetTitle() {
        TextFilter textFilter = new TextFilter();
        
        textFilter.setTitle("This is a title for the TextFilter");
        
        assertEquals(
            "The title label was expected to have \"This is a title for the TextFilter\" as text", 
            "This is a title for the TextFilter", 
            textFilter.getTitleLabel().getText()
        );
    }
