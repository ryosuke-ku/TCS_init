	public E first()
	{
		final Object[] node = this.firstNode();
		if (node == null)
		{
			throw new NoSuchElementException();
		}
		return this.getNodeKey(node);
	}
