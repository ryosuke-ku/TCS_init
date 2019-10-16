	public void registerTable(Class<?> tableClass)
			throws TableRegistrationException {

		TableRegistration table = TableFactory.buildTable(tableClass);
		if (table == null) {
			throw new TableRegistrationException("The TableFactory returned a null table");
		}

		for (TableRegistration t : getTables().values()) {
			if (t.getName().equalsIgnoreCase(table.getName()))
				throw new TableRegistrationException("This table matches another with the same name!");
		}
		super.registerTable(tableClass);
	}
