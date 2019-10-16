	public boolean removeAll(Collection<?> c)
	{
		boolean changed = false;
		
		java.util.Iterator<?> iter = c.iterator();
		
		while (iter.hasNext())
		{
			Object val = iter.next();
			changed |= this.remove(val);
		}
		
		if (iter instanceof ReleasableIterator)
		{
			((ReleasableIterator<?>)iter).release();
		}

		return changed;
	}
