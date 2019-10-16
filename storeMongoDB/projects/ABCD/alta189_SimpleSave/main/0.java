	public void connect() throws ConnectionException {
		if (!isConnected()) {
			try {
				super.connect();
				Class.forName("org.sqlite.JDBC");
				connection = DriverManager.getConnection(uri);
				createTables();
				if (checkTableOnRegistration()) {
					for (TableRegistration t : getTableRegistrations()){
						checkTableStructure(t);
					}
				}
			} catch (ClassNotFoundException e) {
				throw new ConnectionException("Could not find the SQLite JDBC driver!", e);
			} catch (SQLException sql) {
				throw new ConnectionException(sql);
			}
		}
	}
