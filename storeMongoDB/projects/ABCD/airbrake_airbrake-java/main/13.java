	public String toString() {
		final StringBuilder stringBuilder = new StringBuilder();
		for (final String string : filteredBacktrace) {
			stringBuilder.append(string).append("\n");
		}
		return stringBuilder.toString();
	}
