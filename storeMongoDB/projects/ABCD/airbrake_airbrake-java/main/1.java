	private final List<String> filter(final List<String> backtrace) {
		final ListIterator<String> iterator = backtrace.listIterator();
		while (iterator.hasNext()) {
			String string = iterator.next();
			if (mustBeIgnored(string)) {
				continue;
			}
			if (notValidBacktrace(string)) {
				string = removeDobuleDot(string);
			}
			filteredBacktrace.add(string);
		}

		return filteredBacktrace;
	}
