	public @Nullable E pollFirst()
	{
		Object[] node = this.pollFirstNode();
		return node == null ? null : this.getNodeKey(node);
	}
