    public void setDescription(String description) {
        // Only override if different than current value, or if already
        // overridden.
        if (!isSame(description, super.getDescription()) || this.description != null) {
            this.description = description;
            super.setDescription(description);
        }
    }
