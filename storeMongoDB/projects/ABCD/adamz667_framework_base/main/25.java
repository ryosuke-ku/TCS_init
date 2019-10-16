    public int delete(String table, String whereClause, String[] whereArgs) {
        SQLiteStatement statement =  new SQLiteStatement(this, "DELETE FROM " + table +
                (!TextUtils.isEmpty(whereClause) ? " WHERE " + whereClause : ""), whereArgs);
        try {
            return statement.executeUpdateDelete();
        } catch (SQLiteDatabaseCorruptException e) {
            onCorruption();
            throw e;
        } finally {
            statement.close();
        }
    }
