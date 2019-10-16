	public void testParameterParser() throws Exception {

		if (!versionMeetsMinimum(5, 0)) {
			return;
		}

		CallableStatement cstmt = null;

		try {

			createTable("t1",
					"(id   char(16) not null default '', data int not null)");
			createTable("t2", "(s   char(16),  i   int,  d   double)");

			createProcedure("foo42",
					"() insert into test.t1 values ('foo', 42);");
			this.conn.prepareCall("{CALL foo42()}");
			this.conn.prepareCall("{CALL foo42}");

			createProcedure("bar",
					"(x char(16), y int, z DECIMAL(10)) insert into test.t1 values (x, y);");
			cstmt = this.conn.prepareCall("{CALL bar(?, ?, ?)}");

			if (!isRunningOnJdk131()) {
				ParameterMetaData md = cstmt.getParameterMetaData();
				assertEquals(3, md.getParameterCount());
				assertEquals(Types.CHAR, md.getParameterType(1));
				assertEquals(Types.INTEGER, md.getParameterType(2));
				assertEquals(Types.DECIMAL, md.getParameterType(3));
			}

			createProcedure("p", "() label1: WHILE @a=0 DO SET @a=1; END WHILE");
			this.conn.prepareCall("{CALL p()}");

			createFunction("f", "() RETURNS INT NO SQL return 1; ");
			cstmt = this.conn.prepareCall("{? = CALL f()}");

			if (!isRunningOnJdk131()) {
				ParameterMetaData md = cstmt.getParameterMetaData();
				assertEquals(Types.INTEGER, md.getParameterType(1));
			}
		} finally {
			if (cstmt != null) {
				cstmt.close();
			}
		}
	}
