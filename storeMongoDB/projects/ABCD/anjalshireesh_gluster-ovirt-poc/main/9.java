    public String GetTagIdsAndChildrenIdsByRegExp(String tagNameRegExp) {
        // add RegEx chars or beginning of string ('^') and end of string ('$'):
        tagNameRegExp = String.format("^%1$s$", tagNameRegExp);
        // convert to the regular expression format:
        tagNameRegExp = tagNameRegExp.replace("*", ".*");
        StringBuilder sb = new StringBuilder();
        RecursiveGetTagsAndChildrenByRegExp(tagNameRegExp, sb, GetRootTag(), TagReturnValueIndicator.ID);
        return sb.toString();
    }
