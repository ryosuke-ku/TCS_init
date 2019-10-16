        public ResultType invoke(Object event, ResultType defaultValue ) throws InvocationTargetException, IllegalAccessException {
            final Object instance = instanceReference.get();
            return instance == null ? defaultValue : (ResultType) method.invoke(instance, event);
        }
