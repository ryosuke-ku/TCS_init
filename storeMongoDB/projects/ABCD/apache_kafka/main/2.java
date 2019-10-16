    public boolean needRejoin() {
        if (!subscriptions.partitionsAutoAssigned())
            return false;

        // we need to rejoin if we performed the assignment and metadata has changed
        if (assignmentSnapshot != null && !assignmentSnapshot.equals(metadataSnapshot))
            return true;

        // we need to join if our subscription has changed since the last join
        if (joinedSubscription != null && !joinedSubscription.equals(subscriptions.subscription()))
            return true;

        return super.needRejoin();
    }
