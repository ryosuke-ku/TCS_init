    public void setVersion(int version) {
        execSQL("PRAGMA user_version = " + version);
    }
