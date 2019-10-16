    public void testAddReferencedColumns()
    {
        Set<DBColumn> cols = new HashSet<DBColumn>();
        expr.addReferencedColumns(cols);
        assertTrue(cols.contains(testDB.EMPLOYEE.FIRSTNAME));
    }
