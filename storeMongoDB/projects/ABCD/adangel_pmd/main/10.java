    public void setName(String name) {
        // Only override if different than current value, or if already
        // overridden.
        if (!isSame(name, super.getName()) || this.name != null) {
            this.name = name;
        }
    }
