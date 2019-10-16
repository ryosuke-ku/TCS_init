    public void testIsLoaded() {
        System.setProperty("dexmaker.dexcache", getContext().getCacheDir().getPath());

        Context c = getContext();
        SurveyListener l = Mockito.mock(SurveyListener.class);
        QuestionInteractionListener qil = Mockito.mock(QuestionInteractionListener.class);
        QuestionGroup qg = Mockito.mock(QuestionGroup.class);

        QuestionGroupTab tab = new QuestionGroupTab(c, qg, l, qil);

        assertEquals(false, tab.isLoaded());
        tab.load();
        assertEquals(true, tab.isLoaded());
    }
