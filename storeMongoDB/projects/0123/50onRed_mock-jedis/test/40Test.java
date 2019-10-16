	public void testInvalidKeyTypeHashToString() {
		j.hset("test", "test", "1");
		j.get("test");
	}
