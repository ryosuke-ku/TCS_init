    public void addMixin(String mixinName) throws RepositoryException {
        try {
            addMixin(sessionContext.getQName(mixinName));
        } catch (NameException e) {
            throw new RepositoryException(
                    "Invalid mixin type name: " + mixinName, e);
        }
    }
