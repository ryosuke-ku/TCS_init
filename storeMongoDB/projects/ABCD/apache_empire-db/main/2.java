	public String getMutatorName(DBColumn column) 
	{
		return deriveMutatorName(column.getName(), getJavaType(column));
	}
