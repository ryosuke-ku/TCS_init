    public void setPriority(RulePriority priority) {
        // Only override if different than current value, or if already
        // overridden.
        if (priority != super.getPriority() || this.priority != null) {
            this.priority = priority;
            super.setPriority(priority);
        }
    }
