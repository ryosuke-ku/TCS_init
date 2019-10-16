	public void registerOutParameter(String parameterName, int sqlType,
			String typeName) throws SQLException {
		registerOutParameter(getNamedParamIndex(parameterName, true), sqlType,
				typeName);
	}
