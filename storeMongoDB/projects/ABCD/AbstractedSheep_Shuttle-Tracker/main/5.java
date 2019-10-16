	public synchronized void clearParameters() throws SQLException {
		super.clearParameters();

		try {
			if (this.outputParameterResults != null) {
				this.outputParameterResults.close();
			}
		} finally {
			this.outputParameterResults = null;
		}
	}
