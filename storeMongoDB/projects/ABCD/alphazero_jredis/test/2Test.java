	public void testToBytes() {
		Log.log("Testing number to bytes conversion ...");
		byte[] javadata = null;
		byte[] data = null;

		// test MIN 
		int n;
		n=Integer.MIN_VALUE;
		javadata = Integer.toString(n).getBytes();
		data = Convert.toBytes(n);
		assertEquals (data.length, javadata.length, "buffer length");
		for(int j=0; j<data.length;j++)
			assertEquals(data[j], javadata[j], "for <"+n+"> byte @ ["+j+"]");

		// test MAX
		n=Integer.MAX_VALUE;
		javadata = Integer.toString(n).getBytes();
		data = Convert.toBytes(n);
		assertEquals (data.length, javadata.length, "buffer length");
		for(int j=0; j<data.length;j++)
			assertEquals(data[j], javadata[j], "for <"+n+"> byte @ ["+j+"]");

		// test a bit smaller than Convert.INT_N_65535
		n=Convert.INT_N_65535 - 444;
		javadata = Integer.toString(n).getBytes();
		data = Convert.toBytes(n);
		assertEquals (data.length, javadata.length, "buffer length");
		for(int j=0; j<data.length;j++)
			assertEquals(data[j], javadata[j], "for <"+n+"> byte @ ["+j+"]");

		// test a bit larger than Convert.INT_P_65535
		n=Convert.INT_P_65535 + 444;
		javadata = Integer.toString(n).getBytes();
		data = Convert.toBytes(n);
		assertEquals (javadata.length, data.length, "buffer length");
		for(int j=0; j<data.length;j++)
			assertEquals( data[j], javadata[j], "for <"+n+"> byte @ ["+j+"]");

		// test the exact range range
		for(int i=Convert.INT_N_65535; i<Convert.INT_P_65535; i++){
			//		for(int i=Integer.MIN_VALUE; i<Integer.MAX_VALUE; i++){
			javadata = Integer.toString(i).getBytes();
			data = Convert.toBytes(i);
			assertEquals (data.length, javadata.length, "buffer length" );
			for(int j=0; j<data.length;j++)
				assertEquals( data[j], javadata[j], "for <"+i+"> byte @ ["+j+"]");
		}		
	}
