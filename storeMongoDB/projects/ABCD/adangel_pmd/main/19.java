    public void setExternalInfoUrl(String externalInfoUrl) {
        // Only override if different than current value, or if already
        // overridden.
        if (!isSame(externalInfoUrl, super.getExternalInfoUrl()) || this.externalInfoUrl != null) {
            this.externalInfoUrl = externalInfoUrl;
            super.setExternalInfoUrl(externalInfoUrl);
        }
    }
