    protected void updateCriteriaObject() {
        getCriteria().setFindEmptyEntriesOnly(checkBox.getValue());
        getCriteria().setCriteria(textBox.getValue());
    }
