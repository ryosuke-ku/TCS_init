    public String GetTagIdAndChildrenIds(String tagName) {
        tags tag = GetTagByName(tagName);
        StringBuilder sb = tag.GetTagIdAndChildrenIds();
        return sb.toString();
    }
