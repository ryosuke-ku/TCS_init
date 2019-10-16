    public Property getProperty(final String relPath)
            throws PathNotFoundException, RepositoryException {
        return perform(new SessionOperation<Property>() {
            public Property perform(SessionContext context)
                    throws RepositoryException {
                PropertyId id = resolveRelativePropertyPath(relPath);
                if (id != null) {
                    try {
                        return (Property) itemMgr.getItem(id);
                    } catch (ItemNotFoundException e) {
                        throw new PathNotFoundException(relPath);
                    } catch (AccessDeniedException e) {
                        throw new PathNotFoundException(relPath);
                    }
                } else {
                    throw new PathNotFoundException(relPath);
                }
            }
            public String toString() {
                return "node.getProperty(" + relPath + ")";
            }
        });
    }
