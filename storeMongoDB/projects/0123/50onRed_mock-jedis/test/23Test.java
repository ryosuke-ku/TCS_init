	public void testInvalidKeyTypeListToHash() {
		j.lpush("test", "test");
		j.hgetAll("test");
	}
