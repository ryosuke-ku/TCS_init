    public java.util.ArrayList<tags> GetAllTags() {
        java.util.ArrayList<tags> ret = new java.util.ArrayList<tags>(tagsMapByID.values());
        // remove the root - it is not a real tag:
        ret.remove(GetRootTag());
        return ret;
    }
