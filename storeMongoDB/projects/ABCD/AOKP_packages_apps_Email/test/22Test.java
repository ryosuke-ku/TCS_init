    public void testWriteToHeader() throws Exception {
        MimeMessage message = new MimeMessage();

        message.setHeader("Header1", "value1");
        message.setHeader(MimeHeader.HEADER_ANDROID_ATTACHMENT_STORE_DATA, "value2");
        message.setExtendedHeader("X-Header3", "value3");
        message.setHeader("Header4", "value4");
        message.setExtendedHeader("X-Header5", "value5");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        message.writeTo(out);
        out.close();
        String expectedString =
                "Header1: value1\r\n" +
                "Header4: value4\r\n" +
                "Message-ID: " + message.getMessageId() + "\r\n" +
                "\r\n";
        byte[] expected = expectedString.getBytes();
        byte[] actual = out.toByteArray();
        assertEquals("output length", expected.length, actual.length);
        for (int i = 0; i < actual.length; ++i) {
            assertEquals("output byte["+i+"]", expected[i], actual[i]);
        }
    }
