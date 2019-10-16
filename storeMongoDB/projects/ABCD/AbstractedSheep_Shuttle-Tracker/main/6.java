	public boolean execute() throws SQLException {
		boolean returnVal = false;

		checkClosed();

		checkStreamability();

		synchronized (this.connection.getMutex()) {
			setInOutParamsOnServer();
			setOutParams();

			returnVal = super.execute();

			if (this.callingStoredFunction) {
				this.functionReturnValueResults = this.results;
				this.functionReturnValueResults.next();
				this.results = null;
			}

			retrieveOutParams();
		}

		if (!this.callingStoredFunction) {
			return returnVal;
		}

		// Functions can't return results
		return false;
	}
