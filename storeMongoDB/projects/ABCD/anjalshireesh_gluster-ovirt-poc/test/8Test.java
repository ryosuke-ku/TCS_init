    public void testGetTagIdAndChildrenIdsAsSet() {
        tags tag = createTag("tag1", "desc1");
        tags tag2 = createTag("tag2", "desc2");
        tag.getChildren().add(tag2);
        tag2.setparent_id(tag.getparent_id());
        tagsDirector.AddTag(tag);
        Set<Guid> idsToCheck = new HashSet<Guid>();
        idsToCheck.add(tag.gettag_id());
        idsToCheck.add(tag2.gettag_id());
        HashSet<Guid> idsFromTagsDirector = tagsDirector.GetTagIdAndChildrenIdsAsSet(tag.gettag_id());
        assertEquals(idsToCheck, idsFromTagsDirector);
    }
