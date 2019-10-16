    public void fire(Context context, Object event) {
        if (!isEnabled()) return;

        /*
        if( event.getClass().getAnnotation(Returns.class)!=null )
            throw new RuntimeException("You must use notifyWithResult for events that expect return values");
        */

        final Map<Class<?>, Set<ObserverReference<?>>> methods = registrations.get(context);
        if (methods == null) return;


        final Set<ObserverReference<?>> observers = methods.get(event.getClass());
        if (observers == null) return;

        for (ObserverReference observer : observers) {
            try {
                observer.invoke(event,null);
            } catch (InvocationTargetException e) {
                Ln.e(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
