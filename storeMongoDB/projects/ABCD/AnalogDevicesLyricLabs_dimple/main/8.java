	public boolean containsAll(Collection<?> collection)
	{
		try
		{
			@SuppressWarnings("unchecked")
			Collection<? extends E> c = (Collection<? extends E>) collection;
			return this.containsAll2(c);
		}
		catch (ClassCastException ex)
		{
			return false;
		}
	}
