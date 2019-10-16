	public void test() {
		SQLiteConfiguration config = new SQLiteConfiguration();
		File tmpfile = null;
		try {
			tmpfile = File.createTempFile("h2test_", ".db");
		} catch (IOException e) {
			e.printStackTrace();
			fail("IOException occured: " + e.toString());
		}
		assertNotNull(tmpfile);
		config.setPath(tmpfile.getAbsolutePath().substring(0, tmpfile.getAbsolutePath().indexOf(".db")));
		tmpfile.deleteOnExit();
		SQLiteDatabase db = (SQLiteDatabase) DatabaseFactory.createNewDatabase(config);
		try {
			db.registerTable(TestClass.class);
		} catch (TableRegistrationException e) {
			e.printStackTrace();
			fail("Exception occured too early! " + e.toString());
		}
		try {
			db.registerTable(TestClass2.class);
		} catch (TableRegistrationException e) {
			e.printStackTrace();
			fail("Exception occured too early! " + e.toString());
		}
		try {
			db.connect();
		} catch (ConnectionException e) {
			fail("Failed to connect to database! " + e.toString());
		}
		TestClass one = new TestClass();
		one.setName("Hello World");
		db.save(TestClass.class, one);
		TestClass two = new TestClass();
		two.setName("Cruel World");
		db.save(TestClass.class, two);
		assertEquals(db.getTableRegistration(TestClass.class).getTableClass(), TestClass.class);
		assertEquals(db.select(TestClass.class).execute().find().size(), 2);
		assertEquals(db.select(TestClass.class).where().equal("name", "Hello World").execute().findOne().name, "Hello World");
		try {
			db.close();
		} catch (ConnectionException e) {
			fail("Failed to close database! " + e.toString());
		}
		tmpfile.delete();
	}
