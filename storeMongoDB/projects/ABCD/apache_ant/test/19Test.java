    public void testGetExtensionPoints() {
        List extensions = ih.getExtensionPoints();
        final int adders = 2;
        assertEquals("extension count", adders, extensions.size());

        // this original test assumed something about the order of
        // add(Number) and addConfigured(Map) returned by reflection.
        // Unfortunately the assumption doesn't hold for all VMs
        // (failed on MacOS X using JDK 1.4.2_05) and the possible
        // combinatorics are too hard to check.  We really only want
        // to ensure that the more derived Hashtable can be found
        // before Map.
//        assertExtMethod(extensions.get(0), "add", Number.class,
//                        new Integer(2), new Integer(3));

        // addConfigured(Hashtable) should come before addConfigured(Map)
        assertExtMethod(extensions.get(adders - 2),
                        "addConfigured", Hashtable.class,
                        makeTable("key", "value"), makeTable("1", "2"));

        assertExtMethod(extensions.get(adders - 1), "addConfigured", Map.class,
                        new HashMap(), makeTable("1", "2"));
    }
