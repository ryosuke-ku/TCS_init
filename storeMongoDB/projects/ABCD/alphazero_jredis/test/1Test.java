	public void testCompression() {
		Log.log("Testing compress/decompress of 1000 random 24KB strings ...");
    	int cnt = 1000;
    	int size = 1024 * 24;
    	for(int i=0; i<cnt; i++){
    		String randomString = getRandomString(size);
    		byte[] stringBytes = randomString.getBytes();
    		byte[] compressed = compress(stringBytes);
    		byte[] decompressed = decompress(compressed);
    		Assert.assertEquals(decompressed, stringBytes);
    	}
	}
