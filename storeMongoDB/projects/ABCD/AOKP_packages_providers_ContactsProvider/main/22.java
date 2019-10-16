    protected static String getLocalizedSortOrder(String sortOrder) {
        String localizedSortOrder = sortOrder;
        if (sortOrder != null) {
            String sortKey;
            String sortOrderSuffix = "";
            int spaceIndex = sortOrder.indexOf(' ');
            if (spaceIndex != -1) {
                sortKey = sortOrder.substring(0, spaceIndex);
                sortOrderSuffix = sortOrder.substring(spaceIndex);
            } else {
                sortKey = sortOrder;
            }
            if (TextUtils.equals(sortKey, Contacts.SORT_KEY_PRIMARY)) {
                localizedSortOrder = ContactsColumns.PHONEBOOK_BUCKET_PRIMARY
                    + sortOrderSuffix + ", " + sortOrder;
            } else if (TextUtils.equals(sortKey, Contacts.SORT_KEY_ALTERNATIVE)) {
                localizedSortOrder = ContactsColumns.PHONEBOOK_BUCKET_ALTERNATIVE
                    + sortOrderSuffix + ", " + sortOrder;
            }
        }
        return localizedSortOrder;
    }
