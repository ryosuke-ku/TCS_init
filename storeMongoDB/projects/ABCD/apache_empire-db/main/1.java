	public String getAccessorName(DBColumn column) 
	{
		return deriveAccessorName(column.getName(), getJavaType(column));
	}
