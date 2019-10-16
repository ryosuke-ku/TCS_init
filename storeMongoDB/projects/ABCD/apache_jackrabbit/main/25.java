    public Property setProperty(String name, Binary value)
            throws ValueFormatException, VersionException, LockException,
            ConstraintViolationException, RepositoryException {
        Value v = null;
        if (value != null) {
            v = getSession().getValueFactory().createValue(value);
        }
        return setProperty(name, v);
    }
