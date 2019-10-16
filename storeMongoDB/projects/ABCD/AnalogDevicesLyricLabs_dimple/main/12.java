	public <T> T[] toArray(T[] a)
	{
		T[] array = a;
		if (array.length < this.size())
		{
			array = (T[]) Array.newInstance(a.getClass().getComponentType(), this.size());
		}
		
		Object[] node = this.head;
		int i = 0;
		
		while (true)
		{
			Object[] next = this.getNextNode(node);
			if (next == null)
			{
				break;
			}
			node = next;
			array[i++] = (T)this.getNodeKey(node);
		}
		
		return array;
	}
