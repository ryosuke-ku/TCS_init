    public void resetPermanentError() {
        SharedPreferencesCompat.apply(mStorage.edit().remove(PREFIX + "permanentError"));
    }
