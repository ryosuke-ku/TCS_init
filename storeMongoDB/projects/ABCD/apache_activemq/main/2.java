    public boolean keepAlive() {
        locked = locked && lock != null && lock.isValid() && !hasBeenModified();
        return locked;
    }
