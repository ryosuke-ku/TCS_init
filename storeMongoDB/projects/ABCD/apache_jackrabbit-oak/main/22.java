    protected boolean isSameIDP(@Nonnull ExternalIdentityRef ref) {
        return idp.getName().equals(ref.getProviderName());
    }
