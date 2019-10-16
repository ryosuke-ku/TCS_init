    public void removeMixin(String mixinName) throws RepositoryException {
        try {
            removeMixin(sessionContext.getQName(mixinName));
        } catch (NameException e) {
            throw new RepositoryException(
                    "Invalid mixin type name: " + mixinName, e);
        }
    }
