    public void executeTarget(final String targetName) throws BuildException {

        // sanity check ourselves, if we've been asked to build nothing
        // then we should complain

        if (targetName == null) {
            final String msg = "No target specified";
            throw new BuildException(msg);
        }

        // Sort and run the dependency tree.
        // Sorting checks if all the targets (and dependencies)
        // exist, and if there is any cycle in the dependency
        // graph.
        executeSortedTargets(topoSort(targetName, targets, false));
    }
