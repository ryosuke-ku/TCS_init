	public static final int toInt(byte[] potentiallySignedBytes) throws IllegalArgumentException
	{
		if(null == potentiallySignedBytes) throw new IllegalArgumentException ("null input");
		return toInt(potentiallySignedBytes, 0, potentiallySignedBytes.length);
	}
