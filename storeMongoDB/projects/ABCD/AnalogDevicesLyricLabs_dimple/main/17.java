	public @Nullable E floor(E value)
	{
		Object[] node = this.findFloorNode(value);
		return node == null ? null : this.getNodeKey(node);
	}
