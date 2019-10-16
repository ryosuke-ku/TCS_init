		public int getParameterType(int arg0) throws SQLException {
			checkBounds(arg0);

			return getParameter(arg0 - 1).jdbcType;
		}
