    public void testGetDeviceId() throws Exception {
        final MyContext context = new MyContext(getContext());

        final String id = ExchangeService.getDeviceId(context);

        // Consists of alpha-numeric
        assertTrue(id.matches("^[a-zA-Z0-9]+$"));

        // getDeviceId may have been called in other tests, so we don't check
        // isGetFileStreamPathCalled here.

        context.isGetFileStreamPathCalled = false;
        final String cachedId = ExchangeService.getDeviceId(context);

        // Should be the same.
        assertEquals(id, cachedId);
        // Should be cached.  (If cached, this method won't be called.)
        assertFalse(context.isGetFileStreamPathCalled);
    }
