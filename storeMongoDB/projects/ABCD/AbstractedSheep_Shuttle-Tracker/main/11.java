	public synchronized ParameterMetaData getParameterMetaData()
			throws SQLException {
		if (this.placeholderToParameterIndexMap == null) {
		return (CallableStatementParamInfoJDBC3) this.paramInfo;
		} else {
			return new CallableStatementParamInfoJDBC3(this.paramInfo);
	}
	}
