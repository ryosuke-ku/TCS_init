	public static Builder register(String sysCode, String fullName)
	{
		DataSource current = null;
		if (fullName == null && sysCode == null) throw new NullPointerException();
//		if (fullName != null && fullName.length() > 20) 
//		{ 
//			throw new IllegalArgumentException("full Name '" + fullName + "' must be 20 or less characters"); 
//		}
		
		if (byFullName.containsKey(fullName))
		{
			current = byFullName.get(fullName);
		}
		else if (bySysCode.containsKey(sysCode))
		{
			current = bySysCode.get(sysCode);
		}
		else
		{
			current = new DataSource ();
			registry.add (current);
		}
		
		if (current.urnBase != null)
		{
			byMiriamBase.put (current.urnBase, current);
		}
		
		current.sysCode = sysCode;
		current.fullName = fullName;

		if (isSuitableKey(sysCode))
			bySysCode.put(sysCode, current);
		if (isSuitableKey(fullName))
			byFullName.put(fullName, current);
		
		return new Builder(current);
	}
