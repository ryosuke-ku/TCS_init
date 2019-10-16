    private void checkInverses(CFMetaData cfm) throws Exception
    {
        KeyspaceMetadata keyspace = Schema.instance.getKSMetaData(cfm.ksName);

        // Test thrift conversion
        CFMetaData before = cfm;
        CFMetaData after = ThriftConversion.fromThriftForUpdate(ThriftConversion.toThrift(before), before);
        assert before.equals(after) : String.format("%n%s%n!=%n%s", before, after);

        // Test schema conversion
        Mutation rm = SchemaKeyspace.makeCreateTableMutation(keyspace, cfm, FBUtilities.timestampMicros()).build();
        PartitionUpdate cfU = rm.getPartitionUpdate(Schema.instance.getId(SchemaConstants.SCHEMA_KEYSPACE_NAME, SchemaKeyspace.TABLES));
        PartitionUpdate cdU = rm.getPartitionUpdate(Schema.instance.getId(SchemaConstants.SCHEMA_KEYSPACE_NAME, SchemaKeyspace.COLUMNS));

        UntypedResultSet.Row tableRow = QueryProcessor.resultify(String.format("SELECT * FROM %s.%s", SchemaConstants.SCHEMA_KEYSPACE_NAME, SchemaKeyspace.TABLES),
                                                                 UnfilteredRowIterators.filter(cfU.unfilteredIterator(), FBUtilities.nowInSeconds()))
                                                      .one();
        TableParams params = SchemaKeyspace.createTableParamsFromRow(tableRow);

        UntypedResultSet columnsRows = QueryProcessor.resultify(String.format("SELECT * FROM %s.%s", SchemaConstants.SCHEMA_KEYSPACE_NAME, SchemaKeyspace.COLUMNS),
                                                                UnfilteredRowIterators.filter(cdU.unfilteredIterator(), FBUtilities.nowInSeconds()));
        Set<ColumnDefinition> columns = new HashSet<>();
        for (UntypedResultSet.Row row : columnsRows)
            columns.add(SchemaKeyspace.createColumnFromRow(row, Types.none()));

        assertEquals(cfm.params, params);
        assertEquals(new HashSet<>(cfm.allColumns()), columns);
    }
