    public Book findBook(Long id) {
        return em.find(Book.class, id);
    }
