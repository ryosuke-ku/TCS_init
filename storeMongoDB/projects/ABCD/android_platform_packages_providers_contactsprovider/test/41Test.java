    public void testInDefaultDirectoryData() {
        final ContentValues values = new ContentValues();
        final long contactId = createContact(values, "John", "Doe",
                "18004664411", "goog411@acme.com", StatusUpdates.INVISIBLE, 4, 1, 0,
                StatusUpdates.CAPABILITY_HAS_CAMERA);

        final StringBuilder query = new StringBuilder()
                .append(Data.MIMETYPE).append("='").append(Email.CONTENT_ITEM_TYPE)
                .append("' AND ").append(Email.DATA).append("=? AND ")
                .append(Contacts.IN_DEFAULT_DIRECTORY).append("=1");

        assertEquals(1,
                getCount(Email.CONTENT_URI, query.toString(), new String[]{"goog411@acme.com"}));

        // Fire!
        markInvisible(contactId);

        // Verify: making a contact visible changes the IN_DEFAULT_DIRECTORY data value.
        assertEquals(0,
                getCount(Email.CONTENT_URI, query.toString(), new String[]{"goog411@acme.com"}));
    }
