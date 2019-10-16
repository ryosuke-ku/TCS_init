    protected tags createTag(String name, String description) {
        Guid tagId = Guid.NewGuid();
        tags tag = new tags();
        tag.setdescription(description);
        tag.settag_id(tagId);
        tag.settag_name(name);
        tag.settype(TagsType.GeneralTag);
        tag.setparent_id(tagsDirector.GetRootTag().gettag_id());
        return tag;
    }
