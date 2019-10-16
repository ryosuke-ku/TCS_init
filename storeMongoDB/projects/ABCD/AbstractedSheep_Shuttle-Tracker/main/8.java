	public int executeUpdate() throws SQLException {
		int returnVal = -1;

		checkClosed();

		checkStreamability();

		if (this.callingStoredFunction) {
			execute();

			return -1;
		}

		synchronized (this.connection.getMutex()) {
			setInOutParamsOnServer();
			setOutParams();

			returnVal = super.executeUpdate();

			retrieveOutParams();
		}

		return returnVal;
	}
