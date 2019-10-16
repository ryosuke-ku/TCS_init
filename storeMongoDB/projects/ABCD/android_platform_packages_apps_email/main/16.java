    public void ensureLoaded(final Context context) {
        if (mHostAuthKeyRecv == 0 && mHostAuthRecv == null) {
            throw new IllegalStateException("Trying to load incomplete Account object");
        }
        getOrCreateHostAuthRecv(context).ensureLoaded(context);

        if (mHostAuthKeySend != 0) {
            getOrCreateHostAuthSend(context);
            if (mHostAuthSend != null) {
                mHostAuthSend.ensureLoaded(context);
            }
        }
    }
