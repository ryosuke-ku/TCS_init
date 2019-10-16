	public void testGetTableClassName() {
		CodeGenConfig config = new CodeGenConfig();
		WriterService service = new WriterService(config);
		assertEquals("TestTable", service.getTableClassName("test_table"));
		assertEquals("TestTable", service.getTableClassName("TEST_TABLE"));
		assertEquals("Testtable", service.getTableClassName("TESTTABLE"));
		/*
		 * Hint: Camel casing can be disabled setting PreserverCharacterCase=true
		 */
	}
o relations.", 2, relations.size());

        final DBRelation dbRelation = relations.get(1);
        assertEquals("EMPLOYEES_DEPARTMENT_I_FK", dbRelation.getName());

        final DBReference[] references = dbRelation.getReferences();
        assertEquals("Should reference one column.", 1, references.length);

        final DBReference dbReference = references[0];
		assertEquals(dbReference.getSourceColumn(), employees.getColumn("DEPARTMENT_ID"));
        assertEquals(dbReference.getTargetColumn(), departments.getColumn("DEPARTMENT_ID"));
        
        final DBColumn salary = employees.getColumn("SALARY");

        assertThat(salary.getDataType(), is(DECIMAL));
        assertThat(salary.getSize(), is(121.212));
    }
