    public IDataElement asDataElement() {
        dataElement = dataElement == null ? rootNode == null ? null : toDataElement() : dataElement;

        return dataElement;
    }
