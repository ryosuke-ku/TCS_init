    public void addBuildListener(final BuildListener listener) {
        synchronized (listenersLock) {
            // If the listeners already has this listener, do nothing
            for (int i = 0; i < listeners.length; i++) {
                if (listeners[i] == listener) {
                    return;
                }
            }
            // copy on write semantics
            final BuildListener[] newListeners =
                new BuildListener[listeners.length + 1];
            System.arraycopy(listeners, 0, newListeners, 0, listeners.length);
            newListeners[listeners.length] = listener;
            listeners = newListeners;
        }
    }
