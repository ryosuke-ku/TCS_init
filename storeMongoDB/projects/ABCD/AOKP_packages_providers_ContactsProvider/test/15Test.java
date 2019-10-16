    public void testStreamItemsCleanedUpOnAccountRemoval() {
        Account doomedAccount = new Account("doom", "doom");
        Account safeAccount = mAccount;
        ContactsProvider2 cp = (ContactsProvider2) getProvider();
        mActor.setAccounts(new Account[]{doomedAccount, safeAccount});
        cp.onAccountsUpdated(new Account[]{doomedAccount, safeAccount});

        // Create a doomed raw contact, stream item, and photo.
        long doomedRawContactId = RawContactUtil.createRawContactWithName(mResolver, doomedAccount);
        Uri doomedStreamItemUri =
                insertStreamItem(doomedRawContactId, buildGenericStreamItemValues(), doomedAccount);
        long doomedStreamItemId = ContentUris.parseId(doomedStreamItemUri);
        Uri doomedStreamItemPhotoUri = insertStreamItemPhoto(
                doomedStreamItemId, buildGenericStreamItemPhotoValues(0), doomedAccount);

        // Create a safe raw contact, stream item, and photo.
        long safeRawContactId = RawContactUtil.createRawContactWithName(mResolver, safeAccount);
        Uri safeStreamItemUri =
                insertStreamItem(safeRawContactId, buildGenericStreamItemValues(), safeAccount);
        long safeStreamItemId = ContentUris.parseId(safeStreamItemUri);
        Uri safeStreamItemPhotoUri = insertStreamItemPhoto(
                safeStreamItemId, buildGenericStreamItemPhotoValues(0), safeAccount);
        long safeStreamItemPhotoId = ContentUris.parseId(safeStreamItemPhotoUri);

        // Remove the doomed account.
        mActor.setAccounts(new Account[]{safeAccount});
        cp.onAccountsUpdated(new Account[]{safeAccount});

        // Check that the doomed stuff has all been nuked.
        ContentValues[] noValues = new ContentValues[0];
        assertStoredValues(ContentUris.withAppendedId(RawContacts.CONTENT_URI, doomedRawContactId),
                noValues);
        assertStoredValues(doomedStreamItemUri, noValues);
        assertStoredValues(doomedStreamItemPhotoUri, noValues);

        // Check that the safe stuff lives on.
        assertStoredValue(RawContacts.CONTENT_URI, safeRawContactId, RawContacts._ID,
                safeRawContactId);
        assertStoredValue(safeStreamItemUri, StreamItems._ID, safeStreamItemId);
        assertStoredValue(safeStreamItemPhotoUri, StreamItemPhotos._ID, safeStreamItemPhotoId);
    }
