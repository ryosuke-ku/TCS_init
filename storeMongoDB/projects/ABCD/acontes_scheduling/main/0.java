    public List<Node> select(int number, List<Node> from) {

        if (from.size() == 0) {
            // no nodes after selection scripts execution
            // return empty list
            return new LinkedList<Node>();
        }

        // initializing cluster distances map
        // cluster is a group of nodes, initially each cluster consist of one node
        logger.debug("Initializing clusters map");
        HashMap<Cluster<Node>, HashMap<Cluster<Node>, Long>> clusterDistances = initClusterDistances(from);

        // no topology information for provided nodes
        if (from.size() > 0 && clusterDistances.size() == 0) {
            throw new TopologyException("Topology information is not available");
        }

        Cluster<Node> target = null;
        if (pivot.size() > 0) {
            // fixed orientation clustering
            Iterator<Node> it = pivot.iterator();
            Node pivotNode = it.next();
            target = new Cluster<Node>(getNodeId(pivotNode), pivotNode);
            // merging pivot nodes into one cluster and recalculating distances
            logger.debug("Merging pivot nodes into one cluster");
            while (it.hasNext()) {
                // merging clusters and recalculating distances between others
                pivotNode = it.next();
                Cluster<Node> pivotCluster = new Cluster<Node>(getNodeId(pivotNode), pivotNode);
                target = recalculateDistances(target, pivotCluster, clusterDistances);
            }

            // clustering centralized to the pivot
            logger.debug("Begin centralized hierarchical agglomerative clustering");
            while (clusterDistances.size() > 1 && target.size() < (number + pivot.size())) {
                Cluster<Node> closest = findClosestClustersTo(target, clusterDistances);

                if (closest == null) {
                    // no clusters found => cannot merge anything => stop where we are
                    break;
                }
                // merging clusters and recalculating distances between others
                target = recalculateDistances(target, closest, clusterDistances);
            }

            // removing pivot nodes from the result
            target.remove(pivot);
        } else {
            logger.debug("Begin hierarchical agglomerative clustering");
            target = (Cluster<Node>) clusterDistances.keySet().iterator().next();
            Cluster<Node> largest = target;
            // floating clustering
            while (clusterDistances.size() > 1) {
                // finding two clusters to merge according
                Cluster<Node>[] clustersToMerge = findClosestClusters(clusterDistances);
                if (clustersToMerge == null) {
                    // there is no clusters close to each other
                    // stop the process
                    break;
                }
                // merging clusters and recalculating distances between others
                target = recalculateDistances(clustersToMerge[0], clustersToMerge[1], clusterDistances);
                if (target.size() >= largest.size()) {
                    largest = target;
                }

                if (target.size() == number) {
                    // found all the nodes we need
                    break;
                } else if (target.size() > number) {
                    // found more nodes that we need,
                    // target cluster contains all nodes from another cluster
                    // largest is the target here

                    logger.debug("Number of node in the cluster exceeded required node number " +
                        target.size() + " vs " + number);

                    Cluster<Node> anotherCluster = clustersToMerge[0] == target ? clustersToMerge[1]
                            : clustersToMerge[0];
                    target.removeLast(anotherCluster.size());
                    final Cluster<Node> finalTarget = target;

                    Comparator<Node> nodeDistanceComparator = new Comparator<Node>() {
                        public int compare(Node n1, Node n2) {
                            long res = getDistance(n1, finalTarget) - getDistance(n2, finalTarget);
                            if (res < 0) {
                                return -1;
                            } else if (res > 0) {
                                return 1;
                            } else {
                                return 0;
                            }
                        }
                    };
                    // sorting nodes in the smaller cluster according to their distances to target
                    Collections.sort(anotherCluster.getElements(), nodeDistanceComparator);

                    int neededNodesNumber = number - target.size();
                    target.add(anotherCluster.getElements().subList(0, neededNodesNumber));
                    break;
                }
            }
            target = largest;
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Found " + target.size() + " nodes out of " + number + ": " + target);
        }
        return target.getElements();
    }
