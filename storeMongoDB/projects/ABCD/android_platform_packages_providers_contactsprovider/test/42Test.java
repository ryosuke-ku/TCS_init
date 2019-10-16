    public void testPhotoStoreCleanupForProfile() {
        SynchronousContactsProvider2 provider = (SynchronousContactsProvider2) mActor.provider;
        PhotoStore profilePhotoStore = provider.getProfilePhotoStore();

        // Trigger an initial cleanup so another one won't happen while we're running this test.
        provider.switchToProfileModeForTest();
        provider.cleanupPhotoStore();

        // Create the profile contact and add a photo.
        Account socialAccount = new Account("social", "social");
        ContentValues values = new ContentValues();
        values.put(RawContacts.ACCOUNT_NAME, socialAccount.name);
        values.put(RawContacts.ACCOUNT_TYPE, socialAccount.type);
        long profileRawContactId = createBasicProfileContact(values);
        long profileContactId = queryContactId(profileRawContactId);
        long dataId = ContentUris.parseId(
                insertPhoto(profileRawContactId, R.drawable.earth_normal));
        long profilePhotoFileId =
                getStoredLongValue(ContentUris.withAppendedId(Data.CONTENT_URI, dataId),
                        Photo.PHOTO_FILE_ID);

        // Also add a stream item with a photo.
        Uri streamItemUri =
                insertStreamItem(profileRawContactId, buildGenericStreamItemValues(),
                        socialAccount);
        long streamItemId = ContentUris.parseId(streamItemUri);
        Uri streamItemPhotoUri = insertStreamItemPhoto(
                streamItemId, buildGenericStreamItemPhotoValues(0), socialAccount);
        long streamItemPhotoFileId = getStoredLongValue(streamItemPhotoUri,
                StreamItemPhotos.PHOTO_FILE_ID);

        // Remove the stream item photo and the profile photo.
        profilePhotoStore.remove(profilePhotoFileId);
        profilePhotoStore.remove(streamItemPhotoFileId);

        // Manually trigger another cleanup in the provider.
        provider.switchToProfileModeForTest();
        provider.cleanupPhotoStore();

        // The following things should have happened.

        // The stream item photo should have been removed.
        assertStoredValues(Uri.withAppendedPath(
                ContentUris.withAppendedId(StreamItems.CONTENT_URI, streamItemId),
                StreamItems.StreamItemPhotos.CONTENT_DIRECTORY),
                new ContentValues[0]);

        // The profile photo should have been cleared.
        assertNull(getStoredValue(
                ContentUris.withAppendedId(Contacts.CONTENT_URI, profileContactId),
                Contacts.PHOTO_FILE_ID));

    }
