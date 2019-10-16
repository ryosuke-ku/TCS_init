	private List<Map<String, Object>> readTableData(final Connection connection, final String connectorId,
			final SourceDatabaseConfiguration sourceConfiguration, final TableMetaData tableMetaData, final int lines) throws SQLException {
		final List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		final String tableName = _connectorRepository.getConnectorHint(connectorId, TableNameMapper.class).getValue()
				.mapTableName(tableMetaData);
		final CommonColumnTypeResolverTool commonColumnTypeResolver = new CommonColumnTypeResolverTool(_connectorRepository);
		final ColumnNameMapper sourceColumnNameMapper = _connectorRepository.getConnectorHint(connectorId, ColumnNameMapper.class).getValue();

		final PreparedStatement selectStatement = new SelectStatementCreator(_connectorRepository, connectorId).createSelectStatement(
						connection, tableName, tableMetaData);
		selectStatement.setFetchSize(lines);

		sourceConfiguration.beforeSelect(connection, connectorId, tableMetaData);
		final ResultSet resultSet = selectStatement.executeQuery();
		sourceConfiguration.afterSelect(connection, connectorId, tableMetaData);

		final List<ColumnMetaData> orderedSourceColumns = ColumnOrderHint.getSortedColumns(_connectorRepository, connectorId, tableMetaData);

		int rowIndex = 1;

		try {
			while (resultSet.next() && rowIndex <= lines) {
				final Map<String, Object> rowData = new HashMap<String, Object>();
				for (int columnIndex = 1; columnIndex <= orderedSourceColumns.size(); columnIndex++) {
					final ColumnMetaData sourceColumn = orderedSourceColumns.get(columnIndex - 1);
					final String columnName = sourceColumnNameMapper.mapColumnName(sourceColumn);
					final ColumnType sourceColumnType = commonColumnTypeResolver.getColumnType(connectorId, sourceColumn);
					final Object data = sourceColumnType.getValue(resultSet, columnIndex);

					rowData.put(columnName, data);
				}

				result.add(rowData);
				rowIndex++;
			}
		} finally {
			try {
				resultSet.close();
				selectStatement.close();
			} catch (final Exception e) {
			}
		}

		return result;
	}
