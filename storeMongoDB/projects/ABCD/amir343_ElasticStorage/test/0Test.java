	public void test_hashCode() {
		Block block1 = new Block("test1", 1212333);
		Block block2 = new Block("test1", 1212333);
		
		Assert.assertEquals("hashCode is not working", block1.hashCode(), block2.hashCode());
		
	}
