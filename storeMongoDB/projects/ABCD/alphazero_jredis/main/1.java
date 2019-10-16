    public static final byte[] decompress(byte[] data){
		ByteArrayOutputStream buffer = null;
		GZIPInputStream gizpInputStream= null;
		try {
			buffer = new ByteArrayOutputStream();
			gizpInputStream = new GZIPInputStream(new ByteArrayInputStream(data));
			int n=-1;
			@SuppressWarnings("unused")
			int tot = 0;
			byte[] _buffer = new byte[1024 * 12];
			while(-1 != (n = gizpInputStream.read(_buffer))){
				buffer.write(_buffer, 0, n);
				tot += n;
			}
			gizpInputStream.close();
			buffer.close();
		} 
		catch (IOException e) {
			throw new RuntimeException("Failed to GZip decompress data", e);
		} 
		return buffer.toByteArray();
    }
