    public String getExtendedHeaders() {
        if (mExtendedHeader != null) {
            return mExtendedHeader.writeToString();
        }
        return null;
    }
