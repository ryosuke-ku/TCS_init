	public static final long toLong(byte[] potentiallySignedBytes) throws IllegalArgumentException
	{
		if(null == potentiallySignedBytes) throw new IllegalArgumentException ("null input");
		return toLong(potentiallySignedBytes, 0, potentiallySignedBytes.length);
	}
