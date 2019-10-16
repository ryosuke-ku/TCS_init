    public void shouldGenerateAIsbnNumber() throws Exception {
        // Checks the ISBN is not null
        assertNotNull("ISBN should not be null", new IsbnGenerator().generateNumber());
    }
