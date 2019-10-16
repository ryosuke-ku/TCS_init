    public void testContactEntitiesWithLookupUri() {
        ContentValues values = new ContentValues();
        Account account1 = new Account("act1", "actype1");
        Account account2 = new Account("act2", "actype2");

        long rawContactId1 = createRawContactWithName(account1);
        insertImHandle(rawContactId1, Im.PROTOCOL_GOOGLE_TALK, null, "gtalk");
        insertStatusUpdate(Im.PROTOCOL_GOOGLE_TALK, null, "gtalk", StatusUpdates.IDLE, "Busy", 90,
                StatusUpdates.CAPABILITY_HAS_CAMERA, false);

        long rawContactId2 = createRawContact(account2);
        setAggregationException(
                AggregationExceptions.TYPE_KEEP_TOGETHER, rawContactId1, rawContactId2);

        long contactId = queryContactId(rawContactId1);
        String lookupKey = queryLookupKey(contactId);

        // First try with a matching contact ID
        Uri contactLookupUri = ContactsContract.Contacts.getLookupUri(contactId, lookupKey);
        Uri entityUri = Uri.withAppendedPath(contactLookupUri, Contacts.Entity.CONTENT_DIRECTORY);
        assertEntityRows(entityUri, contactId, rawContactId1, rawContactId2);

        // Now try with a contact ID mismatch
        contactLookupUri = ContactsContract.Contacts.getLookupUri(contactId + 1, lookupKey);
        entityUri = Uri.withAppendedPath(contactLookupUri, Contacts.Entity.CONTENT_DIRECTORY);
        assertEntityRows(entityUri, contactId, rawContactId1, rawContactId2);

        // Now try without an ID altogether
        contactLookupUri = Uri.withAppendedPath(Contacts.CONTENT_LOOKUP_URI, lookupKey);
        entityUri = Uri.withAppendedPath(contactLookupUri, Contacts.Entity.CONTENT_DIRECTORY);
        assertEntityRows(entityUri, contactId, rawContactId1, rawContactId2);
    }
