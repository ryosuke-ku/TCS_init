    public void testMoveTag_root() {
        //let's have two top level tag under root
        tags level1obj1 = createTag("level1obj1", "");
        level1obj1.settag_id(Guid.NewGuid());
        level1obj1.setparent_id(tagsDirector.GetRootTag().gettag_id());
        tagsDirector.AddTag(level1obj1);
        tags level1obj2 = createTag("level1obj2", "");
        level1obj2.settag_id(Guid.NewGuid());
        level1obj2.setparent_id(tagsDirector.GetRootTag().gettag_id());
        tagsDirector.AddTag(level1obj2);

        //now none of these should have any children
        Assert.assertEquals(0, tagsDirector.GetTagById(level1obj1.gettag_id()).getChildren().size());
        Assert.assertEquals(0, tagsDirector.GetTagById(level1obj2.gettag_id()).getChildren().size());
        Assert.assertEquals(2, tagsDirector.GetRootTag().getChildren().size());

        //should be all right so far.
        //now let's do the trick: move the second level tag to under the other first level tag
        tagsDirector.MoveTag(level1obj1.gettag_id(), level1obj2.gettag_id());

        //and now let's recheck, the first top level should have 0 children, the second should have 1
        Assert.assertEquals(1, tagsDirector.GetTagById(level1obj2.gettag_id()).getChildren().size());
        Assert.assertEquals(1, tagsDirector.GetRootTag().getChildren().size());

    }
