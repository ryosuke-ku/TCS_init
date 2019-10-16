	public void test_simpleStuff()
	{
		String name = "name";
		FactorFunction ff = new CustomFactorFunctionWrapper(name);
		assertTrue(ff.getName() == name);
		//should kaboom
		ff.eval(new Object[]{.5, .5});
	}
