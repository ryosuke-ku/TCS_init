	public void save(Class<?> tableClass, Object o) {
		if (!isConnected()) {
			try {
				connect();
			} catch (ConnectionException e) {
				throw new RuntimeException(e);
			}
		}
		if (!tableClass.isAssignableFrom(o.getClass())) {
			throw new IllegalArgumentException("The provided table class and save objects classes were not compatible.");
		}

		TableRegistration table = getTableRegistration(tableClass);

		if (table == null) {
			throw new UnknownTableException("The table class '" + tableClass.getCanonicalName() + "' is not registered!");
		}

		StringBuilder buffer = new StringBuilder();
		long id = TableUtils.getIdValue(table, o);
		if (id == 0) {
			buffer.append("INSERT INTO ")
					.append(table.getName())
					.append(" (");
			StringBuilder values = new StringBuilder();
			values.append("VALUES ( ");
			int iter = 0;
			for (FieldRegistration fieldRegistration : table.getFields()) {
				iter++;
				buffer.append(fieldRegistration.getName());
				values.append("?");
				if (iter == table.getFields().size()) {
					buffer.append(") ");
					values.append(")");
				} else {
					buffer.append(", ");
					values.append(", ");
				}
			}
			buffer.append(values.toString());
		} else {
			buffer.append("UPDATE ")
					.append(table.getName())
					.append(" SET ");
			int iter = 0;
			for (FieldRegistration fieldRegistration : table.getFields()) {
				iter++;
				buffer.append(fieldRegistration.getName())
						.append("=?");
				if (iter != table.getFields().size()) {
					buffer.append(", ");
				}
			}
			buffer.append(" WHERE ")
					.append(table.getId().getName())
					.append(" = ?");
		}

		try {
			PreparedStatement statement;
			if (id == 0) {
				statement = connection.prepareStatement(buffer.toString(), Statement.RETURN_GENERATED_KEYS);
			} else {
				statement = connection.prepareStatement(buffer.toString());
			}
			int i = 0;
			for (FieldRegistration fieldRegistration : table.getFields()) {
				i++;
				if (fieldRegistration.isSerializable()) {
					PreparedStatementUtils.setObject(statement, i, o);
				} else {
					if (fieldRegistration.getType().equals(int.class) || fieldRegistration.getType().equals(Integer.class)) {
						PreparedStatementUtils.setObject(statement, i, (Integer) TableUtils.getValue(fieldRegistration, o));
					} else if (fieldRegistration.getType().equals(long.class) || fieldRegistration.getType().equals(Long.class)) {
						PreparedStatementUtils.setObject(statement, i, (Long) TableUtils.getValue(fieldRegistration, o));
					} else if (fieldRegistration.getType().equals(double.class) || fieldRegistration.getType().equals(Double.class)) {
						PreparedStatementUtils.setObject(statement, i, (Double) TableUtils.getValue(fieldRegistration, o));
					} else if (fieldRegistration.getType().equals(String.class)) {
						PreparedStatementUtils.setObject(statement, i, (String) TableUtils.getValue(fieldRegistration, o));
					} else if (fieldRegistration.getType().equals(boolean.class) || fieldRegistration.getType().equals(Boolean.class)) {
						boolean value = (Boolean) TableUtils.getValue(fieldRegistration, o);
						if (value) {
							PreparedStatementUtils.setObject(statement, i, 1);
						} else {
							PreparedStatementUtils.setObject(statement, i, 0);
						}
					} else if (fieldRegistration.getType().equals(short.class) || fieldRegistration.getType().equals(Short.class)) {
						PreparedStatementUtils.setObject(statement, i, (Short) TableUtils.getValue(fieldRegistration, o));
					} else if (fieldRegistration.getType().equals(float.class) || fieldRegistration.getType().equals(Float.class)) {
						PreparedStatementUtils.setObject(statement, i, (Float) TableUtils.getValue(fieldRegistration, o));
					} else if (fieldRegistration.getType().equals(byte.class) || fieldRegistration.getType().equals(Byte.class)) {
						PreparedStatementUtils.setObject(statement, i, (Byte) TableUtils.getValue(fieldRegistration, o));
					} else {
						PreparedStatementUtils.setObject(statement, i, (Object) TableUtils.getValue(fieldRegistration, o));
					}
				}
			}

			if (id != 0) {
				i++;
				IdRegistration idRegistration = table.getId();
				if (idRegistration.getType().equals(Integer.class) || idRegistration.getType().equals(int.class)) {
					PreparedStatementUtils.setObject(statement, i, (Integer) TableUtils.getValue(idRegistration, o));
				} else if (idRegistration.getType().equals(Long.class) || idRegistration.getType().equals(long.class)) {
					PreparedStatementUtils.setObject(statement, i, (Long) TableUtils.getValue(idRegistration, o));
				}
			}

			statement.executeUpdate();
			if (id == 0) {
				ResultSet resultSet = statement.getGeneratedKeys();
				if (resultSet != null && resultSet.next()) {
					try {
						Field field = table.getId().getField();
						field.setAccessible(true);
						if (table.getId().getType().equals(int.class)) {
							field.setInt(o, resultSet.getInt(1));
						} else if (table.getId().getType().equals(Integer.class)) {
							field.set(o, resultSet.getObject(1));
						} else if (table.getId().getType().equals(long.class)) {
							field.setLong(o, resultSet.getLong(1));
						} else if (table.getId().getType().equals(Long.class)) {
							field.set(o, resultSet.getObject(1));
						}
					} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
					}
				}
			}
			table.executePostInitialize(o);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
