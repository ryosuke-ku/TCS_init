    public void registerObserver(Context context, Object instance, Method method, Class event) {
        if (!isEnabled()) return;

        if( context instanceof Application )
            throw new RuntimeException("You may not register event handlers on the Application context");

        Map<Class<?>, Set<ObserverReference<?>>> methods = registrations.get(context);
        if (methods == null) {
            methods = new HashMap<Class<?>, Set<ObserverReference<?>>>();
            registrations.put(context, methods);
        }

        Set<ObserverReference<?>> observers = methods.get(event);
        if (observers == null) {
            observers = new HashSet<ObserverReference<?>>();
            methods.put(event, observers);
        }

        /*
        final Returns returns = (Returns) event.getAnnotation(Returns.class);
        if( returns!=null ) {
            if( !returns.value().isAssignableFrom(method.getReturnType()) )
                throw new RuntimeException( String.format("Method %s.%s does not return a value that is assignable to %s",method.getDeclaringClass().getName(),method.getName(),returns.value().getName()) );

            if( !observers.isEmpty() ) {
                final ObserverReference observer = observers.iterator().next();
                throw new RuntimeException( String.format("Only one observer allowed for event types that return a value annotation.  Previously registered observer is %s.%s", observer.method.getDeclaringClass().getName(), observer.method.getName()));
            }
        }
        */

        observers.add(new ObserverReference(instance, method));
    }
