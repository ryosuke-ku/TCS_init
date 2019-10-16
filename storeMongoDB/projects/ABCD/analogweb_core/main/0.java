    public Object invoke() throws InvocationFailureException {
        Object[] args = asList().toArray(new Object[argumentList.size()]);
        try {
            Method method = ReflectionUtils.getInvocationMethod(metadata);
            if (method == null) {
                throw new InvocationFailureException(new NoSuchMethodException(), metadata, args);
            }
            return method.invoke(invocationInstance, args);
        } catch (IllegalArgumentException e) {
            throw new InvocationFailureException(e, metadata, args);
        } catch (IllegalAccessException e) {
            throw new InvocationFailureException(e, metadata, args);
        } catch (InvocationTargetException e) {
            Throwable th = e.getCause();
            throw new InvocationFailureException(th, metadata, args);
        }
    }
