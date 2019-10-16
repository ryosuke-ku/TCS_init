	public void setString(String parameterName, String x) throws SQLException {
		setString(getNamedParamIndex(parameterName, false), x);
	}
