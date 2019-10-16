	public synchronized int getInt(String parameterName) throws SQLException {
		ResultSetInternalMethods rs = getOutputParameters(0); // definitely not going to be
		// from ?=

		int retValue = rs.getInt(fixParameterName(parameterName));

		this.outputParamWasNull = rs.wasNull();

		return retValue;
	}
