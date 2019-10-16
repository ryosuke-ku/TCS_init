	public boolean noDiffs()
	{
		return  _childrenInANotB == null &&
				_childrenInBNotA == null &&
				_childDiffs == null;
	}
