	public @Nullable E ceiling(E value)
	{
		Object[] node = this.findCeilingNode(value);
		return node == null ? null : this.getNodeKey(node);
	}
