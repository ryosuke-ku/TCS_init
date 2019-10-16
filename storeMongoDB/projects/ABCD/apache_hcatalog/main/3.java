    public boolean isOwner() {
        return id != null && ownerId != null && id.equals(ownerId);
    }
