    public PrivilegeBits supportedPrivileges(@Nullable Tree tree, @Nullable PrivilegeBits privilegeBits) {
        if (tree == null) {
            return PrivilegeBits.EMPTY;
        }

        PrivilegeBits pb;
        if (privilegeBits == null) {
            pb = PrivilegeBits.BUILT_IN.get(PrivilegeConstants.JCR_READ);
        } else {
            pb = PrivilegeBits.getInstance(privilegeBits);
            pb.retain(PrivilegeBits.BUILT_IN.get(PrivilegeConstants.JCR_READ));
        }

        if (pb.isEmpty() || !includesCug(tree)) {
            return PrivilegeBits.EMPTY;
        } else {
            return pb;
        }
    }
