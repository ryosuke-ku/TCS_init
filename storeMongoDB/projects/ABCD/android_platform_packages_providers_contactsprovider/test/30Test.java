    public void testQueryCorpContactsProvider() throws Exception {
        final ContactsProvider2 provider = (ContactsProvider2) getProvider();
        final MockUserManager um = mActor.mockUserManager;
        final Uri enterpriseUri =
                Uri.withAppendedPath(PhoneLookup.ENTERPRISE_CONTENT_FILTER_URI, "408-222-2222");
        final Uri invalidAuthorityUri = android.provider.Settings.Secure.CONTENT_URI;

        // No corp user.  Primary only.
        assertEquals(-1, UserUtils.getCorpUserId(mActor.getProviderContext()));
        assertEquals(0, provider.queryCorpContactsProvider(enterpriseUri, null, null, null,
                null, null).getCount());

        final SynchronousContactsProvider2 corpCp2 = setUpCorpProvider();
        // Insert a contact to the corp CP2
        long rawContactId = ContentUris.parseId(
                corpCp2.insert(RawContacts.CONTENT_URI, new ContentValues()));
        // Insert a name
        ContentValues cv = cv(
                Data.RAW_CONTACT_ID, rawContactId,
                Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE,
                StructuredName.DISPLAY_NAME, "Contact2 Corp",
                StructuredName.GIVEN_NAME, "Contact2",
                StructuredName.FAMILY_NAME, "Corp");
        corpCp2.insert(ContactsContract.Data.CONTENT_URI, cv);
        // Insert a number
        cv = cv(
                Data.RAW_CONTACT_ID, rawContactId,
                Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE,
                Phone.NUMBER, "408-222-2222",
                Phone.TYPE, Phone.TYPE_HOME);
        corpCp2.insert(ContactsContract.Data.CONTENT_URI, cv);
        // Primary + corp
        um.setUsers(MockUserManager.PRIMARY_USER, MockUserManager.CORP_USER);
        // It returns 2 identical rows, probably because of the join in phone_lookup.
        assertEquals(2, provider.queryCorpContactsProvider(enterpriseUri, null, null, null,
                null, null).getCount());
        try {
            provider.queryCorpContactsProvider(invalidAuthorityUri, null, null,
                    null, null, null);
            fail(invalidAuthorityUri.toString() + " should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }
