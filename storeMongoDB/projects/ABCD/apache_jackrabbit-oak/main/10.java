    public long supportedPermissions(@Nonnull TreePermission treePermission, @Nullable PropertyState property, long permissions) {
        long supported = permissions & Permissions.READ;
        if (supported != Permissions.NO_PERMISSION && (treePermission instanceof CugTreePermission) && ((CugTreePermission) treePermission).isInCug()) {
            return supported;
        } else {
            return Permissions.NO_PERMISSION;
        }
    }
