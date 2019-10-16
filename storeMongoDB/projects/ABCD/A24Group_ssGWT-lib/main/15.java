    protected void clearFields() {
        checkBox.setValue(false);
        fromDateBox.setValue(null);
        toDateBox.setValue(null);
        filterList.setSelectedIndex(0);
        fromDateBox.setEnabled(true);
        toDateBox.setEnabled(true);
        filterList.setEnabled(true);
    }
