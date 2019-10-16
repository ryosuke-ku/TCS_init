    public void testSms() {
        Uri base = Uri.parse("content://sms");
        Uri appended = base.buildUpon()
                .appendEncodedPath("conversations/addr=555-1212")
                .build();
        assertEquals("content://sms/conversations/addr=555-1212",
                appended.toString());
        assertEquals(2, appended.getPathSegments().size());
        assertEquals("conversations", appended.getPathSegments().get(0));
        assertEquals("addr=555-1212", appended.getPathSegments().get(1));
    }
