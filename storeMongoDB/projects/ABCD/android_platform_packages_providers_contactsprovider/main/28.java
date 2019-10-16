    protected Cursor queryCorpContactsProvider(Uri localUri, String[] projection,
            String selection, String[] selectionArgs, String sortOrder,
            CancellationSignal cancellationSignal) {
        final int corpUserId = UserUtils.getCorpUserId(getContext());
        if (corpUserId < 0) {
            return createEmptyCursor(localUri, projection);
        }
        // Make sure authority is CP2 not other providers
        if (!ContactsContract.AUTHORITY.equals(localUri.getAuthority())) {
            Log.w(TAG, "Invalid authority: " + localUri.getAuthority());
            throw new IllegalArgumentException(
                    "Authority " + localUri.getAuthority() + " is not a valid CP2 authority.");
        }
        final Uri remoteUri = maybeAddUserId(localUri, corpUserId);
        Cursor cursor = getContext().getContentResolver().query(remoteUri, projection, selection,
                selectionArgs, sortOrder, cancellationSignal);
        if (cursor == null) {
            return createEmptyCursor(localUri, projection);
        }
        return cursor;
    }
