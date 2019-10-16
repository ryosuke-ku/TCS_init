    public boolean contains(Object searchValue)
    {
		try
		{
			@SuppressWarnings("unchecked")
			E val = (E)searchValue;
			return this.contains2(val);
		}
		catch (ClassCastException ex)
		{
			return false;
		}
    }
