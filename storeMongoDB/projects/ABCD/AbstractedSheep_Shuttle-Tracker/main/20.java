	public void setInt(String parameterName, int x) throws SQLException {
		setInt(getNamedParamIndex(parameterName, false), x);
	}
