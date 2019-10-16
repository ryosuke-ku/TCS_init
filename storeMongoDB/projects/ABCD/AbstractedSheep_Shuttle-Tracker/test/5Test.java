	public void testOutParams() throws Exception {
		if (versionMeetsMinimum(5, 0)) {
			CallableStatement storedProc = null;

			createProcedure("testOutParam", "(x int, out y int)\n" + "begin\n"
					+ "declare z int;\n" + "set z = x+1, y = z;\n" + "end\n");

			storedProc = this.conn.prepareCall("{call testOutParam(?, ?)}");

			storedProc.setInt(1, 5);
			storedProc.registerOutParameter(2, Types.INTEGER);

			storedProc.execute();

			System.out.println(storedProc);

			int indexedOutParamToTest = storedProc.getInt(2);

			if (!isRunningOnJdk131()) {
				int namedOutParamToTest = storedProc.getInt("y");

				assertTrue("Named and indexed parameter are not the same",
						indexedOutParamToTest == namedOutParamToTest);
				assertTrue("Output value not returned correctly",
						indexedOutParamToTest == 6);

				// Start over, using named parameters, this time
				storedProc.clearParameters();
				storedProc.setInt("x", 32);
				storedProc.registerOutParameter("y", Types.INTEGER);

				storedProc.execute();

				indexedOutParamToTest = storedProc.getInt(2);
				namedOutParamToTest = storedProc.getInt("y");

				assertTrue("Named and indexed parameter are not the same",
						indexedOutParamToTest == namedOutParamToTest);
				assertTrue("Output value not returned correctly",
						indexedOutParamToTest == 33);

				try {
					storedProc.registerOutParameter("x", Types.INTEGER);
					assertTrue(
							"Should not be able to register an out parameter on a non-out parameter",
							true);
				} catch (SQLException sqlEx) {
					if (!SQLError.SQL_STATE_ILLEGAL_ARGUMENT.equals(sqlEx
							.getSQLState())) {
						throw sqlEx;
					}
				}

				try {
					storedProc.getInt("x");
					assertTrue(
							"Should not be able to retreive an out parameter on a non-out parameter",
							true);
				} catch (SQLException sqlEx) {
					if (!SQLError.SQL_STATE_COLUMN_NOT_FOUND.equals(sqlEx
							.getSQLState())) {
						throw sqlEx;
					}
				}
			}

			try {
				storedProc.registerOutParameter(1, Types.INTEGER);
				assertTrue(
						"Should not be able to register an out parameter on a non-out parameter",
						true);
			} catch (SQLException sqlEx) {
				if (!SQLError.SQL_STATE_ILLEGAL_ARGUMENT.equals(sqlEx
						.getSQLState())) {
					throw sqlEx;
				}
			}
		}
	}
