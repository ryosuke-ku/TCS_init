    public String generateNumber() {
        String isbn = "13-84356-" + number++;
        logger.fine("IsbnGenerator.generateNumber():" + isbn);
        return isbn;
    }
