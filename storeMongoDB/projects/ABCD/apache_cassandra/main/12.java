        public static Builder create(String keyspace, String table, boolean isDense, boolean isCompound, boolean isSuper, boolean isCounter)
        {
            return new Builder(keyspace, table, isDense, isCompound, isSuper, isCounter, false);
        }
