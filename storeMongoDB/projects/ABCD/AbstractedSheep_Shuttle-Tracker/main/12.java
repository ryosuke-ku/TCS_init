	public synchronized String getString(String parameterName)
			throws SQLException {
		ResultSetInternalMethods rs = getOutputParameters(0); // definitely not going to be
		// from ?=

		String retValue = rs.getString(fixParameterName(parameterName));

		this.outputParamWasNull = rs.wasNull();

		return retValue;
	}
