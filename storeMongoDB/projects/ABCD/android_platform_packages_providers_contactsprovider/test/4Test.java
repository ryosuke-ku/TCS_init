    public void testCheckForSupportedColumns() {
        final ProjectionMap projectionMap = new ProjectionMap.Builder()
                .add("A").add("B").add("C").build();
        final ContentValues values = new ContentValues();
        values.put("A", "?");
        values.put("C", "?");
        // No exception expected.
        checkForSupportedColumns(projectionMap, values);
        // Expect exception for invalid column.
        EvenMoreAsserts.assertThrows(IllegalArgumentException.class, new Runnable() {
            @Override
            public void run() {
                values.put("D", "?");
                checkForSupportedColumns(projectionMap, values);
            }
        });
    }
