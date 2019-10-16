    public void clear( Context context ) {
        final Map<Class<?>, Set<ObserverReference<?>>> methods = registrations.get(context);
        if (methods == null) return;

        registrations.remove(context);
        methods.clear();
    }
