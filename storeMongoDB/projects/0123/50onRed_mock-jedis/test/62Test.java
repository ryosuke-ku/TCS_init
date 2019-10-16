	public void testInvalidKeyTypeHashToList() {
		j.hset("test", "test", "1");
		j.llen("test");
	}
