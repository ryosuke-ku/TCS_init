	public void test_0() throws Exception {
		SerializeWriter writer = new SerializeWriter();
		writer.append('A');
		writer.writeInt(156);
		Assert.assertEquals("A156", writer.toString());
		writer.writeLong(345);
		Assert.assertEquals("A156345", writer.toString());

	}
