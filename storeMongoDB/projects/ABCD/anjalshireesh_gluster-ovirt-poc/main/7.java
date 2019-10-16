    public java.util.HashSet<Guid> GetTagIdAndChildrenIdsAsSet(Guid tagId) {
        tags tag = GetTagById(tagId);
        java.util.HashSet<Guid> set = new java.util.HashSet<Guid>();
        tag.GetTagIdAndChildrenIdsAsList(set);
        return set;
    }
