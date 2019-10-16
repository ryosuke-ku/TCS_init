	public void test() {
		H2Configuration h2 = new H2Configuration();
		File tmpfile = null;
		try {
			tmpfile = File.createTempFile("h2test_", ".db");
		} catch (IOException e) {
			e.printStackTrace();
			fail("IOException occurred: " + e.toString());
		}
		assertNotNull(tmpfile);
		h2.setDatabase(tmpfile.getAbsolutePath().substring(0, tmpfile.getAbsolutePath().indexOf(".db")));
		tmpfile.deleteOnExit();
		H2Database db = (H2Database) DatabaseFactory.createNewDatabase(h2);
		try {
			db.registerTable(TestClass.class);
		} catch (TableRegistrationException e) {
			e.printStackTrace();
			fail("Exception occurred too early! " + e.toString());
		}
		boolean caught = false;
		try {
			db.registerTable(TestClass2.class);
		} catch (TableRegistrationException e) {
			caught = true;
		}
		if (!caught)
			fail("Failed to catch exception with registering duplicate class!");
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
		assertTrue(one.getPost());
		try {
			db.close();
		} catch (ConnectionException e) {
			fail("Failed to close database! " + e.toString());
		}
		H2Database db2 = (H2Database) DatabaseFactory.createNewDatabase(h2);
		try {
			db2.registerTable(TestClass2.class);
		} catch (TableRegistrationException e) {
			throw new RuntimeException(e);
		}
		try {
			db2.connect();
		} catch (ConnectionException e) {
			throw new RuntimeException(e);
		}
		try {
			db2.close();
		} catch (ConnectionException e) {
			throw new RuntimeException(e);
		}
		try {
			db2.connect();
		} catch (ConnectionException e) {
			throw new RuntimeException(e);
		}
		try {
			db2.close();
		} catch (ConnectionException e) {
			throw new RuntimeException(e);
		}
		tmpfile.delete();
	}
