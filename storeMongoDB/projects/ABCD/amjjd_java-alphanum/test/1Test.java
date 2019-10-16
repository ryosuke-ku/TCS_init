	public void test()
	{
		Comparator<String> comparator = new AlphanumericComparator(Collator.getInstance(Locale.ENGLISH));
		
		// equality
		assertEquals(0, comparator.compare("", ""));
		assertEquals(0, comparator.compare("abc", "abc"));
		assertEquals(0, comparator.compare("123", "123"));
		assertEquals(0, comparator.compare("abc123", "abc123"));
		
		// empty strings < non-empty
		assertTrue(comparator.compare("", "abc") < 0);
		assertTrue(comparator.compare("abc", "") > 0);
		
		// numbers < non numeric
		assertTrue(comparator.compare("123", "abc") < 0);
		assertTrue(comparator.compare("abc", "123") > 0);
		
		// numbers ordered numerically
		assertTrue(comparator.compare("2", "11") < 0);
		assertTrue(comparator.compare("a2", "a11") < 0);
		
		// leading zeroes
		assertTrue(comparator.compare("02", "11") < 0);
		assertTrue(comparator.compare("02", "002") < 0);
		
		// decimal points ... 
		assertTrue(comparator.compare("1.3", "1.5") < 0);
		
		// ... don't work too well
		assertTrue(comparator.compare("1.3", "1.15") < 0);
	}
