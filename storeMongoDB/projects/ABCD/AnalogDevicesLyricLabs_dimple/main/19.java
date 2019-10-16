	public @Nullable E pollLast()
	{
		Object[] node = this.pollLastNode();
		return node == null ? null : this.getNodeKey(node);
	}
