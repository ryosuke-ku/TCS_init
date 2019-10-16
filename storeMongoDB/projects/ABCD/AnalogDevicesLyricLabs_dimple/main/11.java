	public boolean retainAll(Collection<?> c)
	{
		boolean changed = false;
		Iterator<E> iter = this.iterator();
		for (E elt = iter.next(); elt != null; elt = iter.next())
		{
			if (!c.contains(elt))
			{
				iter.remove();
				changed = true;
			}
		}
		iter.release();
		return changed;
	}
