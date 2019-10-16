	public void testLettersAfterEqualNumbers()
	{
		Comparator<String> comparator = AlphanumericComparator.forLocale(Locale.ENGLISH);
		assertTrue(comparator.compare("2AZ", "2ZA") < 0);
	}
