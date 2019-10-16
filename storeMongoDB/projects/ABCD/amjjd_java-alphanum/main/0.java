	public static AlphanumericComparator forLocale(Locale locale)
	{
		Collator collator = Collator.getInstance(locale);
		collator.setDecomposition(Collator.NO_DECOMPOSITION);
		collator.setStrength(Collator.PRIMARY);
		return new AlphanumericComparator(collator);
	}
