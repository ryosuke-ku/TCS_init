    public void testSupportedPrivileges() {
        PrivilegeBits readBits = PrivilegeBits.BUILT_IN.get(PrivilegeConstants.JCR_READ);
        PrivilegeBits readNodeBits = PrivilegeBits.BUILT_IN.get(PrivilegeConstants.REP_READ_NODES);
        PrivilegeBits readPropBits = PrivilegeBits.BUILT_IN.get(PrivilegeConstants.REP_READ_PROPERTIES);
        PrivilegeBitsProvider provider = new PrivilegeBitsProvider(root);

        for (String path : PATH_INCUG_MAP.keySet()) {
            boolean isInCug = PATH_INCUG_MAP.get(path);
            Tree tree = root.getTree(path);
            if (isInCug) {
                assertPrivilegeBits(readBits, cugPermProvider.supportedPrivileges(tree, readBits));
                assertPrivilegeBits(readNodeBits, cugPermProvider.supportedPrivileges(tree, readNodeBits));
                assertPrivilegeBits(readPropBits, cugPermProvider.supportedPrivileges(tree, readPropBits));

                assertPrivilegeBits(readBits, cugPermProvider.supportedPrivileges(tree, provider.getBits(PrivilegeConstants.JCR_ALL)));
                assertPrivilegeBits(readNodeBits, cugPermProvider.supportedPrivileges(tree, provider.getBits(PrivilegeConstants.REP_READ_NODES, PrivilegeConstants.JCR_READ_ACCESS_CONTROL)));

            } else {
                assertTrue(cugPermProvider.supportedPrivileges(tree, readBits).isEmpty());
                assertTrue(cugPermProvider.supportedPrivileges(tree, readNodeBits).isEmpty());
                assertTrue(cugPermProvider.supportedPrivileges(tree, readPropBits).isEmpty());

                assertTrue(cugPermProvider.supportedPrivileges(tree, provider.getBits(PrivilegeConstants.JCR_ALL)).isEmpty());
                assertTrue(cugPermProvider.supportedPrivileges(tree, provider.getBits(PrivilegeConstants.REP_READ_NODES, PrivilegeConstants.JCR_READ_ACCESS_CONTROL)).isEmpty());
            }

            assertTrue(cugPermProvider.supportedPrivileges(tree, provider.getBits(PrivilegeConstants.REP_WRITE)).isEmpty());
            assertTrue(cugPermProvider.supportedPrivileges(tree, provider.getBits(PrivilegeConstants.JCR_ADD_CHILD_NODES, PrivilegeConstants.JCR_REMOVE_CHILD_NODES, PrivilegeConstants.JCR_REMOVE_NODE)).isEmpty());
            assertTrue(cugPermProvider.supportedPrivileges(tree, provider.getBits(PrivilegeConstants.JCR_READ_ACCESS_CONTROL)).isEmpty());
        }
    }
