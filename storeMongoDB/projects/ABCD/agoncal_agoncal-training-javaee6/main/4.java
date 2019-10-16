    public CD createCD(CD cd) {
        em.persist(cd);
        return cd;
    }
