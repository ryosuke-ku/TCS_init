	public void test_2() throws Exception {
		SerializeWriter writer = new SerializeWriter();
		writer.writeIntArray(new int[] { -1 });
		Assert.assertEquals("[-1]", writer.toString());
	}
