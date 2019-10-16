	public static String replaceNonMatchingPatterns(String string, String regexp, String replacement, boolean replaceEachCharacter) {
		if (string == null || string.length() == 0) {
			return string;
		}
		StringBuilder sb = new StringBuilder();
		Matcher m = Pattern.compile(regexp).matcher(string);
		int last = 0;
		while (m.find()) {
			if (replaceEachCharacter) {
				for (int i = last; i < m.start(); i++) {
					sb.append(replacement);
				}
			} else if (last != m.start()) {
				sb.append(replacement);
			}
			sb.append(m.group());
			last = m.end();
		}
		if (replaceEachCharacter) {
			for (int i = last; i < string.length(); i++) {
				sb.append(replacement);
			}
		} else if (last != string.length()) {
			sb.append(replacement);
		}
		return sb.toString();
	}
