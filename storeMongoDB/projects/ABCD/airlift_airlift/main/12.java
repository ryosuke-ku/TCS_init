    public <T> T unwrap(Class<T> iface)
            throws SQLException
    {
        if (iface == null) {
            throw new SQLException("iface is null");
        }

        if (iface.isInstance(this)) {
            return iface.cast(this);
        }
        throw new SQLException(getClass().getName() + " does not implement " + iface.getName());
    }
