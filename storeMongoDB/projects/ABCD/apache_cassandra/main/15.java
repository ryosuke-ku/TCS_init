        public CFMetaData build()
        {
            if (tableId == null)
                tableId = UUIDGen.getTimeUUID();

            List<ColumnDefinition> partitions = new ArrayList<>(partitionKeys.size());
            List<ColumnDefinition> clusterings = new ArrayList<>(clusteringColumns.size());
            PartitionColumns.Builder builder = PartitionColumns.builder();

            for (int i = 0; i < partitionKeys.size(); i++)
            {
                Pair<ColumnIdentifier, AbstractType> p = partitionKeys.get(i);
                partitions.add(new ColumnDefinition(keyspace, table, p.left, p.right, i, ColumnDefinition.Kind.PARTITION_KEY));
            }

            for (int i = 0; i < clusteringColumns.size(); i++)
            {
                Pair<ColumnIdentifier, AbstractType> p = clusteringColumns.get(i);
                clusterings.add(new ColumnDefinition(keyspace, table, p.left, p.right, i, ColumnDefinition.Kind.CLUSTERING));
            }

            for (Pair<ColumnIdentifier, AbstractType> p : regularColumns)
                builder.add(new ColumnDefinition(keyspace, table, p.left, p.right, ColumnDefinition.NO_POSITION, ColumnDefinition.Kind.REGULAR));

            for (Pair<ColumnIdentifier, AbstractType> p : staticColumns)
                builder.add(new ColumnDefinition(keyspace, table, p.left, p.right, ColumnDefinition.NO_POSITION, ColumnDefinition.Kind.STATIC));

            return new CFMetaData(keyspace,
                                  table,
                                  tableId,
                                  isSuper,
                                  isCounter,
                                  isDense,
                                  isCompound,
                                  isView,
                                  partitions,
                                  clusterings,
                                  builder.build(),
                                  partitioner.orElseGet(DatabaseDescriptor::getPartitioner));
        }
