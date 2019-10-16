    public synchronized void unlock() throws RuntimeException {

        if (!isClosed() && id != null) {
            // we don't need to retry this operation in the case of failure
            // as ZK will remove ephemeral files and we don't wanna hang
            // this process when closing if we cannot reconnect to ZK
            try {

                ZooKeeperOperation zopdel = new ZooKeeperOperation() {
                    public boolean execute() throws KeeperException,
                        InterruptedException {
                        zookeeper.delete(id, -1);
                        return Boolean.TRUE;
                    }
                };
                zopdel.execute();
            } catch (InterruptedException e) {
                LOG.warn("Caught: " + e, e);
                //set that we have been interrupted.
                Thread.currentThread().interrupt();
            } catch (KeeperException.NoNodeException e) {
                // do nothing
            } catch (KeeperException e) {
                LOG.warn("Caught: " + e, e);
                throw (RuntimeException) new RuntimeException(e.getMessage()).
                    initCause(e);
            } finally {
                if (callback != null) {
                    callback.lockReleased();
                }
                id = null;
            }
        }
    }
