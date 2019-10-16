    public void testGetAllTags() {
        ArrayList<tags> allTags = tagsDirector.GetAllTags();
        assertEquals(0, allTags.size());
        tags tag = createTag("tag1", "desc1");
        tagsDirector.AddTag(tag);
        allTags = tagsDirector.GetAllTags();
        assertEquals(1, allTags.size());
        tag = createTag("tag2", "desc2");
        tagsDirector.AddTag(tag);
        allTags = tagsDirector.GetAllTags();
        assertEquals(2, allTags.size());
    }
