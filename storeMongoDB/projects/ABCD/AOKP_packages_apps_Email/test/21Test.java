    public void testExtendedHeaders() throws MessagingException {
        MimeMessage message = new MimeMessage();

        assertNull("new message", message.getExtendedHeaders());
        message.setExtendedHeaders(null);
        assertNull("null headers", message.getExtendedHeaders());
        message.setExtendedHeaders("");
        assertNull("empty headers", message.getExtendedHeaders());

        message.setExtendedHeaders("X-Header1: value1\r\n");
        assertEquals("header 1 value", "value1", message.getExtendedHeader("X-Header1"));
        assertEquals("header 1", "X-Header1: value1\r\n", message.getExtendedHeaders());

        message.setExtendedHeaders(null);
        message.setExtendedHeader("X-Header2", "value2");
        message.setExtendedHeader("X-Header3",  "value3\n value4\r\n value5\r\n");
        assertEquals("headers 2,3",
                "X-Header2: value2\r\n" +
                "X-Header3: value3 value4 value5\r\n",
                message.getExtendedHeaders());

        message.setExtendedHeaders(
                "X-Header3: value3 value4 value5\r\n" +
                "X-Header2: value2\r\n");
        assertEquals("header 2", "value2", message.getExtendedHeader("X-Header2"));
        assertEquals("header 3", "value3 value4 value5", message.getExtendedHeader("X-Header3"));
        assertEquals("headers 3,2",
                "X-Header3: value3 value4 value5\r\n" +
                "X-Header2: value2\r\n",
                message.getExtendedHeaders());
    }
