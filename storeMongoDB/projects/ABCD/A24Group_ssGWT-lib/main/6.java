    protected boolean checkFilterActive() {
        if (getCriteria().isFindEmptyEntriesOnly()) {
            return true;
        } else if (getCriteria().getCriteria() != null && !getCriteria().getCriteria().trim().equals("")) {
            return true;
        }
        return false;
    }
