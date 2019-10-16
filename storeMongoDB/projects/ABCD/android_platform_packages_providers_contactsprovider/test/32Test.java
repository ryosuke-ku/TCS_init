    public void testAddQueryParametersFromUri() {
        final ContactsProvider2 provider = (ContactsProvider2) getProvider();
        final Uri originalUri = Phone.CONTENT_FILTER_URI.buildUpon()
                .appendQueryParameter("a", "a")
                .appendQueryParameter("b", "b")
                .appendQueryParameter("c", "c").build();
        final Uri.Builder targetBuilder = Phone.CONTENT_FILTER_URI.buildUpon();
        provider.addQueryParametersFromUri(targetBuilder, originalUri,
                new ArraySet<String>(Arrays.asList(new String[] {
                        "b"
                })));
        final Uri targetUri = targetBuilder.build();
        assertEquals(1, targetUri.getQueryParameters("a").size());
        assertEquals(0, targetUri.getQueryParameters("b").size());
        assertEquals(1, targetUri.getQueryParameters("c").size());
    }
