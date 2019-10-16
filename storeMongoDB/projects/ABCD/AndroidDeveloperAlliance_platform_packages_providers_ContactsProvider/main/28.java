    static Cursor buildSingleRowResult(String[] projection, String[] availableColumns,
            Object[] data) {
        Preconditions.checkArgument(availableColumns.length == data.length);
        if (projection == null) {
            projection = availableColumns;
        }
        final MatrixCursor c = new MatrixCursor(projection, 1);
        final RowBuilder row = c.newRow();

        // It's O(n^2), but it's okay because we only have a few columns.
        for (int i = 0; i < c.getColumnCount(); i++) {
            final String column = c.getColumnName(i);

            boolean found = false;
            for (int j = 0; j < availableColumns.length; j++) {
                if (availableColumns[j].equals(column)) {
                    row.add(data[j]);
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new IllegalArgumentException("Invalid column " + projection[i]);
            }
        }
        return c;
    }
