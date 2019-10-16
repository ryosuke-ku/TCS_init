	public void test_1() throws Exception {
		SerializeWriter writer = new SerializeWriter();
		writer.writeInt(-1);
		Assert.assertEquals("-1", writer.toString());
	}
