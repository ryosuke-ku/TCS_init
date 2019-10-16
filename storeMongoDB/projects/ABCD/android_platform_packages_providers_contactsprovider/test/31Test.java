    public void testConvertEnterpriseUriWithPersonalDirectoryToLocalUri() {
        String phoneNumber = "886";
        String directory = String.valueOf(Directory.DEFAULT);
        boolean isSip = true;
        Uri enterpriseUri = Phone.ENTERPRISE_CONTENT_URI.buildUpon().appendPath(phoneNumber)
                .appendQueryParameter(ContactsContract.DIRECTORY_PARAM_KEY, directory)
                .appendQueryParameter(PhoneLookup.QUERY_PARAMETER_SIP_ADDRESS,
                        String.valueOf(isSip)).build();
        Uri localUri = ContactsProvider2.convertToLocalUri(enterpriseUri, Phone.CONTENT_URI);
        Uri expectedUri = Phone.CONTENT_URI.buildUpon().appendPath(phoneNumber)
                .appendQueryParameter(ContactsContract.DIRECTORY_PARAM_KEY,
                        String.valueOf(Directory.DEFAULT))
                .appendQueryParameter(PhoneLookup.QUERY_PARAMETER_SIP_ADDRESS,
                        String.valueOf(isSip)).build();
        assertUriEquals(expectedUri, localUri);
    }
