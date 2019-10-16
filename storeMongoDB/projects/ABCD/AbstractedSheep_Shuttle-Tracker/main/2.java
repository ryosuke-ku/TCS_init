		public int getParameterCount() throws SQLException {
			if (this.parameterList == null) {
				return 0;
			}
			
			return this.parameterList.size();
		}
