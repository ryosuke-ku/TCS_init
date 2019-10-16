	public <T> QueryResult<T> execute(Query<T> query) {
		if (!isConnected()) {
			try {
				connect();
			} catch (ConnectionException e) {
				throw new RuntimeException(e);
			}
		}

		try {

			// Prepare the query
			switch (query.getType()) {
				case SELECT:
					SelectQuery select = (SelectQuery) query;
					TableRegistration table = getTableRegistration(select.getTableClass());
					PreparedStatement statement = null;
					StringBuilder queryBuilder = new StringBuilder("SELECT * FROM ").append(table.getName()).append(" ");
					if (!select.where().getEntries().isEmpty()) {
						queryBuilder.append("WHERE ");
						int iter = 0;
						for (Object o : select.where().getEntries()) {
							iter++;
							if (!(o instanceof WhereEntry)) {
								continue;
							}

							WhereEntry entry = (WhereEntry) o;
							if (entry.getPrefix() != null && !entry.getPrefix().isEmpty()) {
								queryBuilder.append(entry.getPrefix());
							}
							queryBuilder.append(entry.getField());
							switch (entry.getComparator()) {
								case EQUAL:
									queryBuilder.append("==? ");
									break;
								case NOT_EQUAL:
									queryBuilder.append("!=? ");
									break;
								case GREATER_THAN:
									queryBuilder.append(">? ");
									break;
								case LESS_THAN:
									queryBuilder.append("<? ");
									break;
								case GREATER_THAN_OR_EQUAL:
									queryBuilder.append(">=? ");
									break;
								case LESS_THAN_OR_EQUAL:
									queryBuilder.append("<=?");
									break;
								case CONTAINS:
									queryBuilder.append(" LIKE ?");
									break;
							}
							if (entry.getSuffix() != null && !entry.getSuffix().isEmpty()) {
								queryBuilder.append(entry.getSuffix());
							}
							if (iter != select.where().getEntries().size()) {
								queryBuilder.append(entry.getOperator().name())
										.append(" ");
							}
						}
						if (!select.order().getPairs().isEmpty()) {
							queryBuilder.append("ORDER BY ");
							int track = 0;
							for (Object pair : select.order().getPairs()) {
								track++;
								if (!(pair instanceof OrderPair)) {
									throw new InternalError("Internal Error: Uncastable Object to OrderPair!");
								}
								OrderPair order = (OrderPair) pair;
								queryBuilder.append(order.column).append(" ").append(order.order.name());
								if (track == select.order().getPairs().size()) {
									queryBuilder.append(" ");
								} else { queryBuilder.append(", "); }
							}
						}
						if (select.limit().getLimit() != null) {
							queryBuilder.append("LIMIT ");
							if (select.limit().getStartFrom() != null) {
								queryBuilder.append(select.limit().getStartFrom()).append(", ");
							}
							queryBuilder.append(select.limit().getLimit()).append(" ");
						}

						statement = connection.prepareStatement(queryBuilder.toString());
						iter = 0;
						for (Object o : select.where().getEntries()) {
							iter++;
							if (!(o instanceof WhereEntry)) {
								continue;
							}

							WhereEntry entry = (WhereEntry) o;
							if (entry.getComparator() == Comparator.CONTAINS) {
								statement.setString(iter, "%" + entry.getComparison().getValue().toString() + "%");
							} else {
								PreparedStatementUtils.setObject(statement, iter, entry.getComparison().getValue());
							}
						}
					}

					// Execute and return
					if (statement == null) {
						if (!select.order().getPairs().isEmpty()) {
							queryBuilder.append("ORDER BY ");
							int track = 0;
							for (Object pair : select.order().getPairs()) {
								track++;
								if (!(pair instanceof OrderPair)) {
									throw new InternalError("Internal Error: Uncastable Object to OrderPair!");
								}
								OrderPair order = (OrderPair) pair;
								queryBuilder.append(order.column).append(" ").append(order.order.name());
								if (track == select.order().getPairs().size()) {
									queryBuilder.append(" ");
								} else { queryBuilder.append(", "); }
							}
						}
						if (select.limit().getLimit() != null) {
							queryBuilder.append("LIMIT ");
							if (select.limit().getStartFrom() != null) {
								queryBuilder.append(select.limit().getStartFrom()).append(", ");
							}
							queryBuilder.append(select.limit().getLimit()).append(" ");
						}
						statement = connection.prepareStatement(queryBuilder.toString());
					}
					ResultSet results = statement.executeQuery();
					QueryResult<T> result = new QueryResult<T>(ResultSetUtils.buildResultList(table, (Class<T>) table.getTableClass(), results));
					for (Object object : result.find()) {
						table.executePostInitialize(object);
					}
					return result;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}
