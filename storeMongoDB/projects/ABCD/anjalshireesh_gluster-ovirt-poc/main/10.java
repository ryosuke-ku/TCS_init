    public tags GetTagById(Guid tagId) {
        if (tagsMapByID.containsKey(tagId)) {
            return tagsMapByID.get(tagId);
        } else {
            return null;
        }
    }
