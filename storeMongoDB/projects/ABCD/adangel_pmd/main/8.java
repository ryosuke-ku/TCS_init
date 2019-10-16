    public void setDeprecated(boolean deprecated) {
        // Deprecation does not propagate to the underlying Rule. It is the
        // Rule reference itself which is being deprecated.
        this.deprecated = deprecated ? deprecated : null;
    }
