    public void testGetMimeType() {
        String mimeType1 = MimeTypeHelper.getMimeType(EasyMock.createMock(Feed.class));
        assertEquals("application/atom+xml;type=feed", mimeType1);
        String mimeType2 = MimeTypeHelper.getMimeType(EasyMock.createMock(Entry.class));
        assertEquals("application/atom+xml;type=entry", mimeType2);
        String mimeType3 = MimeTypeHelper.getMimeType(EasyMock.createMock(Service.class));
        assertEquals("application/atomsvc+xml", mimeType3);
    }
