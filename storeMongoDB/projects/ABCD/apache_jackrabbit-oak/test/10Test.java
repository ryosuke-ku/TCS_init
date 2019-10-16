    public void testSupportedPermissionsByLocation() {
        for (String path : PATH_INCUG_MAP.keySet()) {
            boolean isInCug = PATH_INCUG_MAP.get(path);
            TreeLocation location = TreeLocation.create(root, path);

            if (isInCug) {
                assertEquals(path, Permissions.READ, cugPermProvider.supportedPermissions(location, Permissions.READ));
                assertEquals(path, Permissions.READ_NODE, cugPermProvider.supportedPermissions(location, Permissions.READ_NODE));
                assertEquals(path, Permissions.READ_PROPERTY, cugPermProvider.supportedPermissions(location, Permissions.READ_PROPERTY));

                assertEquals(path, Permissions.READ, cugPermProvider.supportedPermissions(location, Permissions.ALL));
                assertEquals(path, Permissions.READ_NODE, cugPermProvider.supportedPermissions(location, Permissions.READ_NODE | Permissions.READ_ACCESS_CONTROL));
            } else {
                assertEquals(path, Permissions.NO_PERMISSION, cugPermProvider.supportedPermissions(location, Permissions.READ));
                assertEquals(path, Permissions.NO_PERMISSION, cugPermProvider.supportedPermissions(location, Permissions.READ_NODE));
                assertEquals(path, Permissions.NO_PERMISSION, cugPermProvider.supportedPermissions(location, Permissions.READ_PROPERTY));

                assertEquals(path, Permissions.NO_PERMISSION, cugPermProvider.supportedPermissions(location, Permissions.ALL));
                assertEquals(path, Permissions.NO_PERMISSION, cugPermProvider.supportedPermissions(location, Permissions.READ_NODE | Permissions.READ_ACCESS_CONTROL));
            }

            assertEquals(path, Permissions.NO_PERMISSION, cugPermProvider.supportedPermissions(location, Permissions.READ_ACCESS_CONTROL));
            assertEquals(path, Permissions.NO_PERMISSION, cugPermProvider.supportedPermissions(location, Permissions.MODIFY_ACCESS_CONTROL));
            assertEquals(path, Permissions.NO_PERMISSION, cugPermProvider.supportedPermissions(location, Permissions.ADD_NODE));
            assertEquals(path, Permissions.NO_PERMISSION, cugPermProvider.supportedPermissions(location, Permissions.WRITE));

            assertEquals(path, Permissions.NO_PERMISSION, cugPermProvider.supportedPermissions(TreeLocation.create(root, "/path/to/no-existing/tree"), Permissions.READ));
        }
    }
