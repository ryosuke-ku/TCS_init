    public synchronized void deploy(int flags)
    {
        // Must resolve if not already resolved.
        if (!m_resolved && !resolve(flags))
        {
            m_logger.log(Logger.LOG_ERROR, "Resolver: Cannot resolve target resources.");
            return;
        }

        // Check to make sure that our local state cache is up-to-date
        // and error if it is not. This is not completely safe, because
        // the state can still change during the operation, but we will
        // be optimistic. This could also be made smarter so that it checks
        // to see if the local state changes overlap with the resolver.
        for (int repoIdx = 0; (m_repositories != null) && (repoIdx < m_repositories.length); repoIdx++)
        {
            if (m_repositories[repoIdx].getLastModified() > m_resolveTimeStamp)
            {
                throw new IllegalStateException("Framework state has changed, must resolve again.");
            }
        }

        // Eliminate duplicates from target, required, optional resources.
        Set<Resource> resourceSet = new HashSet<Resource>();
        Resource[] resources = getAddedResources();
        for (int i = 0; (resources != null) && (i < resources.length); i++)
        {
            resourceSet.add(resources[i]);
        }
        resources = getRequiredResources();
        for (int i = 0; (resources != null) && (i < resources.length); i++)
        {
            resourceSet.add(resources[i]);
        }
        if ((flags & NO_OPTIONAL_RESOURCES) == 0)
        {
            resources = getOptionalResources();
            for (int i = 0; (resources != null) && (i < resources.length); i++)
            {
                resourceSet.add(resources[i]);
            }
        }
        Resource[] deployResources = resourceSet.toArray(new Resource[resourceSet.size()]);

        // List to hold all resources to be started.
        List<Bundle> startList = new ArrayList<Bundle>();

        // Deploy each resource, which will involve either finding a locally
        // installed resource to update or the installation of a new version
        // of the resource to be deployed.
        for (Resource deployResource : deployResources) {
            // For the resource being deployed, see if there is an older
            // version of the resource already installed that can potentially
            // be updated.
            LocalResource localResource = findUpdatableLocalResource(deployResource);
            // If a potentially updatable older version was found,
            // then verify that updating the local resource will not
            // break any of the requirements of any of the other
            // resources being deployed.
            if ((localResource != null) &&
                    isResourceUpdatable(localResource, deployResource, deployResources)) {
                // Only update if it is a different version.
                if (!localResource.equals(deployResource)) {
                    // Update the installed bundle.
                    try {
                        // stop the bundle before updating to prevent
                        // the bundle update from throwing due to not yet
                        // resolved dependencies
                        boolean doStartBundle = (flags & START) != 0;
                        if (localResource.getBundle().getState() == Bundle.ACTIVE) {
                            doStartBundle = true;
                            localResource.getBundle().stop();
                        }

                        localResource.getBundle().update(FileUtil.openURL(new URL(deployResource.getURI())));

                        // If necessary, save the updated bundle to be
                        // started later.
                        if (doStartBundle) {
                            Bundle bundle = localResource.getBundle();
                            if (!isFragmentBundle(bundle)) {
                                startList.add(bundle);
                            }
                        }
                    } catch (Exception ex) {
                        m_logger.log(
                                Logger.LOG_ERROR,
                                "Resolver: Update error - " + getBundleName(localResource.getBundle()),
                                ex);
                        return;
                    }
                }
            } else {
                // Install the bundle.
                try {
                    // Perform the install, but do not use the actual
                    // bundle JAR URL for the bundle location, since this will
                    // limit OBR's ability to manipulate bundle versions. Instead,
                    // use a unique timestamp as the bundle location.
                    URL url = new URL(deployResource.getURI());
                    Bundle bundle = m_context.installBundle(
                            "obr://"
                                    + deployResource.getSymbolicName()
                                    + "/-" + System.currentTimeMillis(),
                            FileUtil.openURL(url));

                    // If necessary, save the installed bundle to be
                    // started later.
                    if ((flags & START) != 0) {
                        if (!isFragmentBundle(bundle)) {
                            startList.add(bundle);
                        }
                    }
                } catch (Exception ex) {
                    m_logger.log(
                            Logger.LOG_ERROR,
                            "Resolver: Install error - " + deployResource.getSymbolicName(),
                            ex);
                    return;
                }
            }
        }

        for (Bundle aStartList : startList) {
            try {
                aStartList.start();
            } catch (BundleException ex) {
                m_logger.log(
                        Logger.LOG_ERROR,
                        "Resolver: Start error - " + aStartList.getSymbolicName(),
                        ex);
            }
        }
    }
