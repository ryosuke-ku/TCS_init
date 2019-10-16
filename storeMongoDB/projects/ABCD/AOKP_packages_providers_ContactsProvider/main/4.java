    public static void checkForSupportedColumns(Set<String> allowedColumns, ContentValues values,
            String msgSuffix) {
        for (String requestedColumn : values.keySet()) {
            if (!allowedColumns.contains(requestedColumn)) {
                throw new IllegalArgumentException("Column '" + requestedColumn + "'. " +
                        msgSuffix);
            }
        }
    }
