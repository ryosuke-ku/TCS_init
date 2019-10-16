    public void testMaxConnections()
            throws Exception
    {
        // datasource server to 1 connection and only wait 1 ms for a connection
        ManagedDataSource dataSource = new MockManagedDataSource(1, new Duration(1, MILLISECONDS));
        assertEquals(dataSource.getMaxConnections(), 1);

        // checkout a connection
        Queue<Connection> connections = new LinkedList<Connection>();
        connections.add(dataSource.getConnection());
        assertEquals(dataSource.getConnectionsActive(), 1);

        // verify that we can't another connection
        try {
            dataSource.getConnection();
            fail("Expected SQLException from timeout");
        }
        catch (SQLException expected) {
        }
        assertEquals(dataSource.getConnectionsActive(), 1);

        // increase the max connection count to 3 and acquire two extra ones
        dataSource.setMaxConnections(3);
        assertEquals(dataSource.getMaxConnections(), 3);
        connections.add(dataSource.getConnection());
        connections.add(dataSource.getConnection());

        // verify that we can't get another connection
        try {
            dataSource.getConnection();
            fail("Expected SQLException from timeout");
        }
        catch (SQLException expected) {
        }
        assertEquals(dataSource.getConnectionsActive(), 3);


        // reduce the max connection count to 2
        dataSource.setMaxConnections(2);
        assertEquals(dataSource.getMaxConnections(), 2);
        assertEquals(dataSource.getConnectionsActive(), 3);

        // first verify that we still can't get more connections
        try {
            dataSource.getConnection();
            fail("Expected SQLException from timeout");
        }
        catch (SQLException expected) {
        }
        assertEquals(dataSource.getConnectionsActive(), 3);

        // now release one and verify we still can't get another one
        connections.remove().close();
        assertEquals(dataSource.getConnectionsActive(), 2);
        try {
            dataSource.getConnection();
            fail("Expected SQLException from timeout");
        }
        catch (SQLException expected) {
        }
        assertEquals(dataSource.getConnectionsActive(), 2);

        // finally close another one and verify we can reopen it
        connections.remove().close();
        connections.add(dataSource.getConnection());
        assertEquals(dataSource.getConnectionsActive(), 2);


        // verify proper handling of illegal values
        try {
            dataSource.setMaxConnectionWaitMillis(null);
            fail("Expected NullPointerException");
        }
        catch (NullPointerException e) {
        }
        assertEquals(dataSource.getMaxConnections(), 2);
        try {
            dataSource.setMaxConnectionWaitMillis(new Duration(0, MILLISECONDS));
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
        }
        assertEquals(dataSource.getMaxConnections(), 2);

        // clean up
        for (Connection connection : connections) {
            connection.close();
        }
        assertEquals(dataSource.getConnectionsActive(), 0);

    }
