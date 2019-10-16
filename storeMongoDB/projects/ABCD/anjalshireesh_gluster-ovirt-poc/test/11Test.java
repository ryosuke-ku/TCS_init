    public void testGetTagByNameNotExists() {
        tags fromTagsDirector = tagsDirector.GetTagByName("does not exist");
        assertNull(fromTagsDirector);
    }
