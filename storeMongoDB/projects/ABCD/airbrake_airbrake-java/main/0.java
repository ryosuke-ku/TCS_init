	public static boolean isValidBacktrace(String string) {
		return string.matches("[^:]*:\\d+.*");
	}
