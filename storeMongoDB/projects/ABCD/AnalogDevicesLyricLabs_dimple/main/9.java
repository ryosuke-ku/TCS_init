	public boolean remove(Object value)
	{
		try
		{
			@SuppressWarnings("unchecked")
			E val = (E)value;
			return this.remove2(val);
		}
		catch (ClassCastException ex)
		{
			return false;
		}
	}
