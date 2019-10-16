    public void testSupplyingBothValuesAndParameters() throws Exception {
        Account account = new Account("account 1", "type%/:1");
        Uri uri = ContactsContract.Groups.CONTENT_URI.buildUpon()
                .appendQueryParameter(ContactsContract.Groups.ACCOUNT_NAME, account.name)
                .appendQueryParameter(ContactsContract.Groups.ACCOUNT_TYPE, account.type)
                .appendQueryParameter(ContactsContract.CALLER_IS_SYNCADAPTER, "true")
                .build();

        ContentProviderOperation.Builder builder = ContentProviderOperation.newInsert(uri);
        builder.withValue(ContactsContract.Groups.ACCOUNT_TYPE, account.type);
        builder.withValue(ContactsContract.Groups.ACCOUNT_NAME, account.name);
        builder.withValue(ContactsContract.Groups.SYSTEM_ID, "some id");
        builder.withValue(ContactsContract.Groups.TITLE, "some name");
        builder.withValue(ContactsContract.Groups.GROUP_VISIBLE, 1);

        mResolver.applyBatch(ContactsContract.AUTHORITY, Lists.newArrayList(builder.build()));

        builder = ContentProviderOperation.newInsert(uri);
        builder.withValue(ContactsContract.Groups.ACCOUNT_TYPE, account.type + "diff");
        builder.withValue(ContactsContract.Groups.ACCOUNT_NAME, account.name);
        builder.withValue(ContactsContract.Groups.SYSTEM_ID, "some other id");
        builder.withValue(ContactsContract.Groups.TITLE, "some other name");
        builder.withValue(ContactsContract.Groups.GROUP_VISIBLE, 1);

        try {
            mResolver.applyBatch(ContactsContract.AUTHORITY, Lists.newArrayList(builder.build()));
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // Expected
        }
    }
