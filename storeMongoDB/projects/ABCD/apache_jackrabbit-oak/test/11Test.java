    public void testIsGrantedByLocation() throws Exception {
        for (String p : NOT_READABLE_PATHS) {
            TreeLocation location = TreeLocation.create(root, p);
            assertFalse(cugPermProvider.isGranted(location, Permissions.READ));
            assertFalse(cugPermProvider.isGranted(location, Permissions.READ_NODE));
            assertFalse(cugPermProvider.isGranted(location, Permissions.READ_PROPERTY));

            assertFalse(cugPermProvider.isGranted(location, Permissions.ALL));
            assertFalse(cugPermProvider.isGranted(location, Permissions.ADD_NODE));
            assertFalse(cugPermProvider.isGranted(location, Permissions.READ_ACCESS_CONTROL));
        }

        for (String p : READABLE_PATHS) {
            TreeLocation location = TreeLocation.create(root, p);
            assertTrue(cugPermProvider.isGranted(location, Permissions.READ));
            assertTrue(cugPermProvider.isGranted(location, Permissions.READ_NODE));
            assertTrue(cugPermProvider.isGranted(location, Permissions.READ_PROPERTY));

            assertFalse(cugPermProvider.isGranted(location, Permissions.ALL));
            assertFalse(cugPermProvider.isGranted(location, Permissions.ADD_NODE));
            assertFalse(cugPermProvider.isGranted(location, Permissions.READ_ACCESS_CONTROL));
        }
    }
