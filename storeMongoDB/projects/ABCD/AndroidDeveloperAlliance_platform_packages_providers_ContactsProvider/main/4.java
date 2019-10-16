    public static void checkForSupportedColumns(HashMap<String, String> projectionMap,
            ContentValues values) {
        for (String requestedColumn : values.keySet()) {
            if (!projectionMap.keySet().contains(requestedColumn)) {
                throw new IllegalArgumentException("Column '" + requestedColumn + "' is invalid.");
            }
        }
    }
