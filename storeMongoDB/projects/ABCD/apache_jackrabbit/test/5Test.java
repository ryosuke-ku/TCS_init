    public void testRepositoryCopy() throws Exception {
        createSourceRepository();
        RepositoryCopier.copy(SOURCE, TARGET);
        verifyTargetRepository();
    }
