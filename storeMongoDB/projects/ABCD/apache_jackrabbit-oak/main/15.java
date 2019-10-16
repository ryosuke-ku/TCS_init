    protected void applyMembership(@Nonnull Authorizable member, @Nonnull Set<String> groups) throws RepositoryException {
        for (String groupName : groups) {
            Authorizable group = userManager.getAuthorizable(groupName);
            if (group == null) {
                log.warn("Unable to apply auto-membership to {}. No such group: {}", member.getID(), groupName);
            } else if (group instanceof Group) {
                ((Group) group).addMember(member);
            } else {
                log.warn("Unable to apply auto-membership to {}. Authorizable '{}' is not a group.", member.getID(), groupName);
            }
        }
    }
