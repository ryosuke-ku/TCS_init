	public void testResultSet() throws Exception {
		if (versionMeetsMinimum(5, 0)) {
			CallableStatement storedProc = null;

			createTable("testSpResultTbl1", "(field1 INT)");
			this.stmt
					.executeUpdate("INSERT INTO testSpResultTbl1 VALUES (1), (2)");
			createTable("testSpResultTbl2", "(field2 varchar(255))");
			this.stmt
					.executeUpdate("INSERT INTO testSpResultTbl2 VALUES ('abc'), ('def')");

			createProcedure(
					"testSpResult",
					"()\n"
							+ "BEGIN\n"
							+ "SELECT field2 FROM testSpResultTbl2 WHERE field2='abc';\n"
							+ "UPDATE testSpResultTbl1 SET field1=2;\n"
							+ "SELECT field2 FROM testSpResultTbl2 WHERE field2='def';\n"
							+ "end\n");

			storedProc = this.conn.prepareCall("{call testSpResult()}");

			storedProc.execute();

			this.rs = storedProc.getResultSet();

			ResultSetMetaData rsmd = this.rs.getMetaData();

			assertTrue(rsmd.getColumnCount() == 1);
			assertTrue("field2".equals(rsmd.getColumnName(1)));
			assertTrue(rsmd.getColumnType(1) == Types.VARCHAR);

			assertTrue(this.rs.next());

			assertTrue("abc".equals(this.rs.getString(1)));

			// TODO: This does not yet work in MySQL 5.0
			// assertTrue(!storedProc.getMoreResults());
			// assertTrue(storedProc.getUpdateCount() == 2);
			assertTrue(storedProc.getMoreResults());

			ResultSet nextResultSet = storedProc.getResultSet();

			rsmd = nextResultSet.getMetaData();

			assertTrue(rsmd.getColumnCount() == 1);
			assertTrue("field2".equals(rsmd.getColumnName(1)));
			assertTrue(rsmd.getColumnType(1) == Types.VARCHAR);

			assertTrue(nextResultSet.next());

			assertTrue("def".equals(nextResultSet.getString(1)));

			nextResultSet.close();

			this.rs.close();

			storedProc.execute();
		}
	}
