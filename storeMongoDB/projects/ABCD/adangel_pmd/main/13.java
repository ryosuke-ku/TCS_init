    public void setMessage(String message) {
        // Only override if different than current value, or if already
        // overridden.
        if (!isSame(message, super.getMessage()) || this.message != null) {
            this.message = message;
            super.setMessage(message);
        }
    }
