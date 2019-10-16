    public boolean IsTagDescestorOfTag(Guid sourceTagId, Guid potentialDescestorId) {
        if (sourceTagId.equals(potentialDescestorId)) {
            return true;
        }
        tags tag = GetTagById(sourceTagId);
        if (tag != null && tag.getChildren() != null) {
            for (tags childTag : tag.getChildren()) {
                if (IsTagDescestorOfTag(childTag.gettag_id(), potentialDescestorId)) {
                    return true;
                }
            }
        }
        return false;
    }
