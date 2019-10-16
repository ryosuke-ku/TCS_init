    public void testIsNonDirectDescetor() {
        tags tag1 = createTag("tag1", "desc1");
        tags tag2 = createTag("tag2", "desc2");
        tags tag3 = createTag("tag3", "desc3");
        tagsDirector.AddTag(tag1);
        tagsDirector.AddTag(tag2);
        tagsDirector.AddTag(tag3);
        tagsDirector.MoveTag(tag3.gettag_id(), tag1.gettag_id());
        tagsDirector.MoveTag(tag2.gettag_id(), tag3.gettag_id());
        assertTrue(tagsDirector.IsTagDescestorOfTag(tag1.gettag_id(), tag2.gettag_id()));
    }
