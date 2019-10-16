    public void shouldOutputChangedStatement() throws Exception {
        la.setPriceCode(Movie.REGULAR);
        verifyOutput(dinsdale.statement(), "outputChange.txt");
    }
