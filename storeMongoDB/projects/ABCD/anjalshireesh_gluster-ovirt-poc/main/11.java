    public tags GetTagByName(String tagName) {
        if (tagsMapByName.containsKey(tagName)) {
            return tagsMapByName.get(tagName);
        } else {
            return null;
        }
    }
