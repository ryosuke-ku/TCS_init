    static Cursor rewriteCorpDirectories(@Nullable Cursor original) {
        if (original == null) {
            return null;
        }
        final String[] projection = original.getColumnNames();
        final MatrixCursor ret = new MatrixCursor(projection);
        original.moveToPosition(-1);
        while (original.moveToNext()) {
            final MatrixCursor.RowBuilder builder = ret.newRow();
            for (int i = 0; i < projection.length; i++) {
                final String outputColumnName = projection[i];
                final int originalColumnIndex = original.getColumnIndex(outputColumnName);
                if (outputColumnName.equals(Directory._ID)) {
                    builder.add(original.getLong(originalColumnIndex)
                            + Directory.ENTERPRISE_DIRECTORY_ID_BASE);
                } else {
                    // Copy the original value.
                    switch (original.getType(originalColumnIndex)) {
                        case Cursor.FIELD_TYPE_NULL:
                            builder.add(null);
                            break;
                        case Cursor.FIELD_TYPE_INTEGER:
                            builder.add(original.getLong(originalColumnIndex));
                            break;
                        case Cursor.FIELD_TYPE_FLOAT:
                            builder.add(original.getFloat(originalColumnIndex));
                            break;
                        case Cursor.FIELD_TYPE_STRING:
                            builder.add(original.getString(originalColumnIndex));
                            break;
                        case Cursor.FIELD_TYPE_BLOB:
                            builder.add(original.getBlob(originalColumnIndex));
                            break;
                    }
                }
            }
        }
        return ret;
    }
