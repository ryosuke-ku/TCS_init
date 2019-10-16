    public void init() {
        if (combinedClassLoader != null) {
            combinedClassLoader.addBundle(bundleContext.getBundle());
        }
        initializationLatch.countDown();
    }
_NAME)) {
                Set<String> newDiscoveredMemberSet = CellarUtils.createSetFromString((String) properties.get(Discovery.DISCOVERED_MEMBERS_PROPERTY_NAME));
                if (!CellarUtils.collectionEquals(discoveredMemberSet, newDiscoveredMemberSet)) {
                    LOGGER.debug("Hazelcast discoveredMemberSet has been changed from {} to {}", discoveredMemberSet, newDiscoveredMemberSet);
                    discoveredMemberSet = newDiscoveredMemberSet;
                    updated = Boolean.TRUE;
                }
            }
        }
        return updated;
    }
