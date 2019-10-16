	public void test_6() throws Exception {
		SerializeWriter writer = new SerializeWriter();
		writer.writeLongAndChar(-1L, ',');
		Assert.assertEquals("-1,", writer.toString());
	}
