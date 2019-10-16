  public org.apache.hadoop.hbase.filter.Filter createFilter(Filter<K, T> filter, HBaseStore<K, T> store) {
    if (filter instanceof FilterList) {
      FilterList<K, T> filterList = (FilterList<K, T>) filter;
      org.apache.hadoop.hbase.filter.FilterList hbaseFilter = new org.apache.hadoop.hbase.filter.FilterList(
          Operator.valueOf(filterList.getOperator().name()));
      for (Filter<K, T> rowFitler : filterList.getFilters()) {
        FilterFactory<K, T> factory = getHbaseFitlerUtil().getFactory(rowFitler);
        if (factory == null) {
          LOG.warn("HBase remote filter factory not yet implemented for " + rowFitler.getClass().getCanonicalName());
          return null;
        }
        org.apache.hadoop.hbase.filter.Filter hbaseRowFilter = factory.createFilter(rowFitler, store);
        if (hbaseRowFilter != null) {
          hbaseFilter.addFilter(hbaseRowFilter);
        }
      }
      return hbaseFilter;
    } else if (filter instanceof SingleFieldValueFilter) {
      SingleFieldValueFilter<K, T> fieldFilter = (SingleFieldValueFilter<K, T>) filter;

      HBaseColumn column = store.getMapping().getColumn(fieldFilter.getFieldName());
      CompareOp compareOp = getCompareOp(fieldFilter.getFilterOp());
      byte[] family = column.getFamily();
      byte[] qualifier = column.getQualifier();
      byte[] value = HBaseByteInterface.toBytes(fieldFilter.getOperands().get(0));
      SingleColumnValueFilter hbaseFilter = new SingleColumnValueFilter(family, qualifier, compareOp, value);
      hbaseFilter.setFilterIfMissing(fieldFilter.isFilterIfMissing());

      return hbaseFilter;
    } else if (filter instanceof MapFieldValueFilter) {
      MapFieldValueFilter<K, T> mapFilter = (MapFieldValueFilter<K, T>) filter;

      HBaseColumn column = store.getMapping().getColumn(mapFilter.getFieldName());
      CompareOp compareOp = getCompareOp(mapFilter.getFilterOp());
      byte[] family = column.getFamily();
      byte[] qualifier = HBaseByteInterface.toBytes(mapFilter.getMapKey());
      byte[] value = HBaseByteInterface.toBytes(mapFilter.getOperands().get(0));
      SingleColumnValueFilter hbaseFilter = new SingleColumnValueFilter(family, qualifier, compareOp, value);
      hbaseFilter.setFilterIfMissing(mapFilter.isFilterIfMissing());

      return hbaseFilter;
    } else {
      LOG.warn("HBase remote filter not yet implemented for " + filter.getClass().getCanonicalName());
      return null;
    }
  }
