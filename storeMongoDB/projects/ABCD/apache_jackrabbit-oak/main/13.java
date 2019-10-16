    protected Group createGroup(@Nonnull ExternalGroup externalGroup) throws RepositoryException {
        Principal principal = new PrincipalImpl(externalGroup.getPrincipalName());
        Group group = userManager.createGroup(
                externalGroup.getId(),
                principal,
                PathUtils.concatRelativePaths(config.group().getPathPrefix(), externalGroup.getIntermediatePath())
        );
        setExternalId(group, externalGroup);
        return group;
    }
