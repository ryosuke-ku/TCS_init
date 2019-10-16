	public java.sql.ResultSet executeQuery() throws SQLException {
		checkClosed();

		checkStreamability();

		java.sql.ResultSet execResults = null;

		synchronized (this.connection.getMutex()) {
			setInOutParamsOnServer();
			setOutParams();

			execResults = super.executeQuery();

			retrieveOutParams();
		}

		return execResults;
	}
