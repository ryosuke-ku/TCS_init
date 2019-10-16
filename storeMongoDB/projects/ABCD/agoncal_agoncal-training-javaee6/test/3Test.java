    public void shouldFindAllBooks() throws Exception {

        // Looks up for the EJB
        ItemEJB itemEJB = (ItemEJB) ctx.lookup("java:global/classes/ItemEJB");

        // Finds all books
        int initialNumberOfBooks = itemEJB.findAllBooks().size();

        // Creates a book
        Book book = new Book("H2G2", 12.5f, "Best IT Scifi Book", 247, false, Language.ENGLISH);

        // Persists the book
        book = itemEJB.createBook(book);
        assertNotNull("ID should not be null", book.getId());

        // Finds all books
        assertEquals("Should have one extra book", initialNumberOfBooks + 1, itemEJB.findAllBooks().size());

        // Deletes the book
        itemEJB.removeBook(book);

        // Finds all books
        assertEquals("Should have initial number of books", initialNumberOfBooks, itemEJB.findAllBooks().size());
    }
