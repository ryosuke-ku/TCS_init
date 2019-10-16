    public static SQLiteDatabase create(CursorFactory factory) {
        // This is a magic string with special meaning for SQLite.
        return openDatabase(MEMORY_DB_PATH, factory, CREATE_IF_NECESSARY);
    }
