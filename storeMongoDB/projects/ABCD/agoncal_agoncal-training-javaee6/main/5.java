    public CD findCD(Long id) {
        return em.find(CD.class, id);
    }
