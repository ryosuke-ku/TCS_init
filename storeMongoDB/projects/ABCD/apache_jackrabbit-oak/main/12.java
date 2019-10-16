    public TreePermission getTreePermission(@Nonnull Tree immutableTree, @Nonnull TreeType type, @Nonnull TreePermission parentPermission) {
        if (!isSupportedType(type) || !topPaths.hasAny()) {
            return TreePermission.NO_RECOURSE;
        }

        TreePermission tp;
        boolean parentIsCugPermission = (parentPermission instanceof CugTreePermission);
        if (TreeType.VERSION == type) {
            tp = createVersionPermission(immutableTree, type, parentPermission, parentIsCugPermission);
        } else {
            if (parentIsCugPermission) {
                tp = new CugTreePermission(immutableTree, type, parentPermission, this);
            } else {
                String path = immutableTree.getPath();
                if (includes(path)) {
                    if (topPaths.contains(path)) {
                        tp = new CugTreePermission(immutableTree, type, parentPermission, this);
                    } else {
                        tp = TreePermission.NO_RECOURSE;
                    }
                } else if (mayContain(path) || isJcrSystemPath(immutableTree)) {
                    tp =  new EmptyCugTreePermission(immutableTree, type, this);
                } else {
                    tp = TreePermission.NO_RECOURSE;
                }
            }
        }
        return tp;
    }
