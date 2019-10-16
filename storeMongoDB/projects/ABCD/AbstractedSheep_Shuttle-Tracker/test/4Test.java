	private void executeBatchedStoredProc(Connection c) throws Exception {
		this.stmt.executeUpdate("TRUNCATE TABLE testBatchTable");

		CallableStatement storedProc = c.prepareCall("{call testBatch(?)}");

		try {
			int numBatches = 300;

			for (int i = 0; i < numBatches; i++) {
				storedProc.setInt(1, i + 1);
				storedProc.addBatch();
			}

			int[] counts = storedProc.executeBatch();

			assertEquals(numBatches, counts.length);

			for (int i = 0; i < numBatches; i++) {
				assertEquals(1, counts[i]);
			}

			this.rs = this.stmt
					.executeQuery("SELECT field1 FROM testBatchTable ORDER BY field1 ASC");

			for (int i = 0; i < numBatches; i++) {
				assertTrue(this.rs.next());
				assertEquals(i + 1, this.rs.getInt(1));
			}
		} finally {

			if (storedProc != null) {
				storedProc.close();
			}
		}
	}
