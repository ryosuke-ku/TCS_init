    protected Value[] createValues(@Nonnull Collection<?> propValues) throws RepositoryException {
        List<Value> values = new ArrayList<Value>();
        for (Object obj : propValues) {
            Value v = createValue(obj);
            if (v != null) {
                values.add(v);
            }
        }
        return values.toArray(new Value[values.size()]);
    }
