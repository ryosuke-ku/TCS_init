    public void unregisterObserver(Context context, Object instance, Class event) {
        if (!isEnabled()) return;

        final Map<Class<?>, Set<ObserverReference<?>>> methods = registrations.get(context);
        if (methods == null) return;

        final Set<ObserverReference<?>> observers = methods.get(event);
        if (observers == null) return;

        for (Iterator<ObserverReference<?>> iterator = observers.iterator(); iterator.hasNext();) {
            ObserverReference observer = iterator.next();
            if (observer != null) {
                final Object registeredInstance = observer.instanceReference.get();
                if (registeredInstance == instance) {
                    iterator.remove();
                    break;
                }
            }
        }
    }
