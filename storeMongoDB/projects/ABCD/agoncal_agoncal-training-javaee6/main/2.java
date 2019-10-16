    public void removeBook(Book book) {
        em.remove(em.merge(book));
    }
