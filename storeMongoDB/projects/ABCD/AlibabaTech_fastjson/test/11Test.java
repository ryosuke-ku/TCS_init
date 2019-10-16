	public void test_4() throws Exception {
		SerializeWriter writer = new SerializeWriter();
		writer.writeIntAndChar(-1, ',');
		Assert.assertEquals("-1,", writer.toString());
	}
