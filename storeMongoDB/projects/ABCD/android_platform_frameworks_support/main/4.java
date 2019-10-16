    public void addDrawerListener(@NonNull DrawerListener listener) {
        if (listener == null) {
            return;
        }
        if (mListeners == null) {
            mListeners = new ArrayList<DrawerListener>();
        }
        mListeners.add(listener);
    }
