    public String resolveViewPathFor(final Route route) {
        if (route.getTargetClass().equals(ErrorTarget.class)) {
            return route.getPath();
        }
        return delegate.resolveViewPathFor(route);
    }
