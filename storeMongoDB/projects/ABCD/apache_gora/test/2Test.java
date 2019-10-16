  public void testReadWriteQuery() throws Exception {
    final int NEMPLOYEE = 100;
    Utils.populateEmployeeStore(employeeStore, NEMPLOYEE);
    InfinispanQuery<String,Employee> query;

    // Partitioning
    int retrieved = 0;
    query = new InfinispanQuery<>(employeeDataStore);
    query.build();
    for (PartitionQuery<String,Employee> q : employeeDataStore.getPartitions(query)) {
      retrieved+=((InfinispanQuery<String,Employee>) q).list().size();
    }
    assert retrieved==NEMPLOYEE;

    // Test matching everything
    query = new InfinispanQuery<>(employeeDataStore);
    SingleFieldValueFilter<String, Employee> filter = new SingleFieldValueFilter<String, Employee>();
    filter.setFieldName("name");
    filter.setFilterOp(FilterOp.EQUALS);
    List<Object> operaands = new ArrayList<>();
    operaands.add("*");
    filter.setOperands(operaands);
    query.setFilter(filter);
    query.build();
    List<Employee> result = new ArrayList<>();
    for (PartitionQuery<String,Employee> q : employeeDataStore.getPartitions(query)) {
      result.addAll(((InfinispanQuery<String,Employee>)q).list());
    }
    assertEquals(NEMPLOYEE,result.size());

    // Test matching nothing
    query = new InfinispanQuery<>(employeeDataStore);
    filter = new SingleFieldValueFilter<String, Employee>();
    filter.setFieldName("name");
    filter.setFilterOp(FilterOp.NOT_EQUALS);
    operaands.clear();
    operaands.add("*");
    filter.setOperands(operaands);
    query.setFilter(filter);
    query.build();
    assertEquals(0,query.list().size());

  }
