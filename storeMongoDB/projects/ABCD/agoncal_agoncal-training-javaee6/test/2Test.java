    public void shouldCreateABookWithTags() throws Exception {

        // Looks up for the EJB
        ItemEJB itemEJB = (ItemEJB) ctx.lookup("java:global/classes/ItemEJB");

        // Creates a book with tags
        Book book = new Book("H2G2", 12.5f, "Best IT Scifi Book", 247, false, Language.ENGLISH);
        List<String> tags = new ArrayList<String>();
        tags.add("scifi");
        tags.add("french");
        book.setTags(tags);

        // Persists the book
        book = itemEJB.createBook(book);
        Long id = book.getId();

        // Finds the book by primary key
        book = itemEJB.findBook(id);
        assertEquals(book.getTitle(), "H2G2");

        // Checks the number of tags
        assertEquals(book.getTags().size(), 2);

        // Deletes the book
        itemEJB.removeBook(book);

        // Checks the book has been deleted
        assertNull("Book should has been deleted", itemEJB.findBook(id));
    }
