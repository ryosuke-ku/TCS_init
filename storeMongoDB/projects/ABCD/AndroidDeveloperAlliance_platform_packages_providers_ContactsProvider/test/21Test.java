    public void testStatusUpdateInsert() {
        long rawContactId = createRawContact();
        Uri imUri = insertImHandle(rawContactId, Im.PROTOCOL_AIM, null, "aim");
        long dataId = ContentUris.parseId(imUri);

        ContentValues values = new ContentValues();
        values.put(StatusUpdates.DATA_ID, dataId);
        values.put(StatusUpdates.PROTOCOL, Im.PROTOCOL_AIM);
        values.putNull(StatusUpdates.CUSTOM_PROTOCOL);
        values.put(StatusUpdates.IM_HANDLE, "aim");
        values.put(StatusUpdates.PRESENCE, StatusUpdates.INVISIBLE);
        values.put(StatusUpdates.STATUS, "Hiding");
        values.put(StatusUpdates.STATUS_TIMESTAMP, 100);
        values.put(StatusUpdates.STATUS_RES_PACKAGE, "a.b.c");
        values.put(StatusUpdates.STATUS_ICON, 1234);
        values.put(StatusUpdates.STATUS_LABEL, 2345);

        Uri resultUri = mResolver.insert(StatusUpdates.CONTENT_URI, values);

        assertStoredValues(resultUri, values);

        long contactId = queryContactId(rawContactId);
        Uri contactUri = ContentUris.withAppendedId(Contacts.CONTENT_URI, contactId);

        values.clear();
        values.put(Contacts.CONTACT_PRESENCE, StatusUpdates.INVISIBLE);
        values.put(Contacts.CONTACT_STATUS, "Hiding");
        values.put(Contacts.CONTACT_STATUS_TIMESTAMP, 100);
        values.put(Contacts.CONTACT_STATUS_RES_PACKAGE, "a.b.c");
        values.put(Contacts.CONTACT_STATUS_ICON, 1234);
        values.put(Contacts.CONTACT_STATUS_LABEL, 2345);

        assertStoredValues(contactUri, values);

        values.clear();
        values.put(StatusUpdates.DATA_ID, dataId);
        values.put(StatusUpdates.STATUS, "Cloaked");
        values.put(StatusUpdates.STATUS_TIMESTAMP, 200);
        values.put(StatusUpdates.STATUS_RES_PACKAGE, "d.e.f");
        values.put(StatusUpdates.STATUS_ICON, 4321);
        values.put(StatusUpdates.STATUS_LABEL, 5432);
        mResolver.insert(StatusUpdates.CONTENT_URI, values);

        values.clear();
        values.put(Contacts.CONTACT_PRESENCE, StatusUpdates.INVISIBLE);
        values.put(Contacts.CONTACT_STATUS, "Cloaked");
        values.put(Contacts.CONTACT_STATUS_TIMESTAMP, 200);
        values.put(Contacts.CONTACT_STATUS_RES_PACKAGE, "d.e.f");
        values.put(Contacts.CONTACT_STATUS_ICON, 4321);
        values.put(Contacts.CONTACT_STATUS_LABEL, 5432);
        assertStoredValues(contactUri, values);
    }
