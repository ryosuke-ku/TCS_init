	public int[] executeBatch() throws SQLException {
		if (this.hasOutputParams) {
			throw SQLError.createSQLException("Can't call executeBatch() on CallableStatement with OUTPUT parameters",
					SQLError.SQL_STATE_ILLEGAL_ARGUMENT, getExceptionInterceptor());
		}
		
		return super.executeBatch();
	}
