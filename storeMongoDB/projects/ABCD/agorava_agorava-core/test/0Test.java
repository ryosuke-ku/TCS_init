    public void currentSessionShouldFallBackToRepoCurrentWithoutSessionIdParam() {
        when(request.getParameter(AgoravaConstants.REPOID_PARAM)).thenReturn(USER_REPO_ID);
        Assert.assertEquals(session2, lfs.getCurrentSession());
    }
