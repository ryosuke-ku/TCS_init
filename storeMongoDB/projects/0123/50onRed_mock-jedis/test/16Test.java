	public void testInvalidKeyTypeStringToHash() {
		j.set("test", "test");
		j.hget("test", "test");
	}
