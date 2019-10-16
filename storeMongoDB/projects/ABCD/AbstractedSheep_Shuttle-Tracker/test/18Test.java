	public void testInOutParams() throws Exception {
		if (versionMeetsMinimum(5, 0)) {
			CallableStatement storedProc = null;

			createProcedure("testInOutParam",
					"(IN p1 VARCHAR(255), INOUT p2 INT)\n" + "begin\n"
							+ " DECLARE z INT;\n" + "SET z = p2 + 1;\n"
							+ "SET p2 = z;\n" + "SELECT p1;\n"
							+ "SELECT CONCAT('zyxw', p1);\n" + "end\n");

			storedProc = this.conn.prepareCall("{call testInOutParam(?, ?)}");

			storedProc.setString(1, "abcd");
			storedProc.setInt(2, 4);
			storedProc.registerOutParameter(2, Types.INTEGER);

			storedProc.execute();

			assertEquals(5, storedProc.getInt(2));

		}
	}
