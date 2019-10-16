    public void testContactSortOrder() {
        assertEquals(ContactsColumns.PHONEBOOK_BUCKET_PRIMARY + ", "
                     + Contacts.SORT_KEY_PRIMARY,
                     ContactsProvider2.getLocalizedSortOrder(Contacts.SORT_KEY_PRIMARY));
        assertEquals(ContactsColumns.PHONEBOOK_BUCKET_ALTERNATIVE + ", "
                     + Contacts.SORT_KEY_ALTERNATIVE,
                     ContactsProvider2.getLocalizedSortOrder(Contacts.SORT_KEY_ALTERNATIVE));
        assertEquals(ContactsColumns.PHONEBOOK_BUCKET_PRIMARY + " DESC, "
                     + Contacts.SORT_KEY_PRIMARY + " DESC",
                     ContactsProvider2.getLocalizedSortOrder(Contacts.SORT_KEY_PRIMARY + " DESC"));
        String suffix = " COLLATE LOCALIZED DESC";
        assertEquals(ContactsColumns.PHONEBOOK_BUCKET_ALTERNATIVE + suffix
                     + ", " + Contacts.SORT_KEY_ALTERNATIVE + suffix,
                     ContactsProvider2.getLocalizedSortOrder(Contacts.SORT_KEY_ALTERNATIVE
                                                             + suffix));
    }
