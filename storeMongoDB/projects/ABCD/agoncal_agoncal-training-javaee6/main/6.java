    public void removeCD(CD cd) {
        em.remove(em.merge(cd));
    }
