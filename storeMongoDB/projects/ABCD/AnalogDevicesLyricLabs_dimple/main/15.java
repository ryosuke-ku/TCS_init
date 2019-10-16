	public E last()
	{
		final Object[] node = this.lastNode();
		if (node == null)
		{
			throw new NoSuchElementException();
		}
		return this.getNodeKey(node);
	}
