    public void testWrapper()
            throws SQLException
    {
        ManagedDataSource dataSource = new MockManagedDataSource(1, new Duration(10, MILLISECONDS));
        assertTrue(dataSource.isWrapperFor(ManagedDataSource.class));
        assertTrue(dataSource.isWrapperFor(DataSource.class));
        assertTrue(dataSource.isWrapperFor(Object.class));
        assertFalse(dataSource.isWrapperFor(ConnectionPoolDataSource.class));
        assertFalse(dataSource.isWrapperFor(Integer.class));
        try {
            dataSource.isWrapperFor(null);
            fail("Expected SQLException");
        }
        catch (SQLException expected) {
        }
        try {
            dataSource.unwrap(null);
            fail("Expected SQLException");
        }
        catch (SQLException expected) {
        }

        assertSame(dataSource.unwrap(ManagedDataSource.class), dataSource);
        assertSame(dataSource.unwrap(DataSource.class), dataSource);
        assertSame(dataSource.unwrap(Object.class), dataSource);

        try {
            dataSource.unwrap(ConnectionPoolDataSource.class);
            fail("Expected SQLException");
        }
        catch (SQLException expected) {
        }

        try {
            dataSource.unwrap(Integer.class);
            fail("Expected SQLException");
        }
        catch (SQLException expected) {
        }
    }
