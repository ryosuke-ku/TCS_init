    public void testSupportedPermissionsByTreePermission() {
        TreePermission rootTp = cugPermProvider.getTreePermission(root.getTree("/"), TreePermission.EMPTY);

        Map<TreePermission, Boolean> tpMap = new HashMap<TreePermission, Boolean>();

        TreePermission contentTp = cugPermProvider.getTreePermission(root.getTree(SUPPORTED_PATH), rootTp);
        tpMap.put(contentTp, false);

        TreePermission aTp = cugPermProvider.getTreePermission(root.getTree("/content/a"), contentTp);
        tpMap.put(aTp, true);
        tpMap.put(cugPermProvider.getTreePermission(root.getTree("/content/a/rep:cugPolicy"), aTp), true);

        TreePermission bTp = cugPermProvider.getTreePermission(root.getTree("/content/a/b"), aTp);
        tpMap.put(bTp, true);
        tpMap.put(cugPermProvider.getTreePermission(root.getTree("/content/a/b/c"), bTp), true);

        TreePermission aaTp = cugPermProvider.getTreePermission(root.getTree("/content/aa"), contentTp);
        tpMap.put(aaTp, false);

        TreePermission bbTp = cugPermProvider.getTreePermission(root.getTree("/content/aa/bb"), aaTp);
        tpMap.put(cugPermProvider.getTreePermission(root.getTree("/content/aa/bb/cc"), bbTp), true);

        // paths that may not contain cugs anyway
        tpMap.put(cugPermProvider.getTreePermission(root.getTree("/jcr:system"), rootTp), false);
        tpMap.put(rootTp, false);
        tpMap.put(cugPermProvider.getTreePermission(root.getTree(UNSUPPORTED_PATH), rootTp), false);

        for (TreePermission tp : tpMap.keySet()) {
            boolean isInCug = tpMap.get(tp);

            if (isInCug) {
                assertEquals(Permissions.READ, cugPermProvider.supportedPermissions(tp, null, Permissions.READ));
                assertEquals(Permissions.READ_NODE, cugPermProvider.supportedPermissions(tp, null, Permissions.READ_NODE));
                assertEquals(Permissions.READ_PROPERTY, cugPermProvider.supportedPermissions(tp, null, Permissions.READ_PROPERTY));
                assertEquals(Permissions.READ, cugPermProvider.supportedPermissions(tp, null, Permissions.ALL));
                assertEquals(Permissions.READ_NODE, cugPermProvider.supportedPermissions(tp, null, Permissions.READ_NODE | Permissions.READ_ACCESS_CONTROL));
            } else {
                assertEquals(Permissions.NO_PERMISSION, cugPermProvider.supportedPermissions(tp, null, Permissions.READ));
                assertEquals(Permissions.NO_PERMISSION, cugPermProvider.supportedPermissions(tp, null, Permissions.READ_NODE));
                assertEquals(Permissions.NO_PERMISSION, cugPermProvider.supportedPermissions(tp, null, Permissions.READ_PROPERTY));
                assertEquals(Permissions.NO_PERMISSION, cugPermProvider.supportedPermissions(tp, null, Permissions.ALL));
                assertEquals(Permissions.NO_PERMISSION, cugPermProvider.supportedPermissions(tp, null, Permissions.READ_NODE | Permissions.READ_ACCESS_CONTROL));
            }
            assertEquals(Permissions.NO_PERMISSION, cugPermProvider.supportedPermissions(tp, null, Permissions.ADD_NODE | Permissions.REMOVE));
        }
    }
