	public int getValueDaysAgo(int daysAgo) {
		if (cache == null)
			getMax();
		return cache[daysAgo];

	}
