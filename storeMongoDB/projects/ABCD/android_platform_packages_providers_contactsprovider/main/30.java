    private Cursor queryCorpContacts(Uri localUri, String[] projection, String selection,
            String[] selectionArgs, String sortOrder, String[] contactIdColumnNames,
            @Nullable Long directoryId, CancellationSignal cancellationSignal) {
        // We need contactId in projection, if it doesn't have, we add it in projection as
        // workProjection, and we restore the actual projection in
        // EnterpriseContactsCursorWrapper
        String[] workProjection = addContactIdColumnIfNotPresent(projection, contactIdColumnNames);
        // Projection is changed only when projection is non-null and does not have contact id
        final boolean isContactIdAdded = (projection == null) ? false
                : (workProjection.length != projection.length);
        final Cursor managedCursor = queryCorpContactsProvider(localUri, workProjection,
                selection, selectionArgs, sortOrder, cancellationSignal);
        int[] columnIdIndices = getContactIdColumnIndices(managedCursor, contactIdColumnNames);
        if (columnIdIndices.length == 0) {
            throw new IllegalStateException("column id is missing in the returned cursor.");
        }
        final String[] originalColumnNames = isContactIdAdded
                ? removeLastColumn(managedCursor.getColumnNames()) : managedCursor.getColumnNames();
        return new EnterpriseContactsCursorWrapper(managedCursor, originalColumnNames,
                columnIdIndices, directoryId);
    }
