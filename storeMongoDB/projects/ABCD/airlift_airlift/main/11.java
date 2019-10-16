    public boolean isWrapperFor(Class<?> iface)
            throws SQLException
    {
        if (iface == null) {
            throw new SQLException("iface is null");
        }

        return iface.isInstance(this);
    }
