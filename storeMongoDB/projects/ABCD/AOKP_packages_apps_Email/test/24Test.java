    public void testSetSubjectPlain() throws MessagingException {
        MimeMessage message = new MimeMessage();

        message.setSubject(SHORT_PLAIN);

        // test 1: readback
        assertEquals("plain subjects", SHORT_PLAIN, message.getSubject());

        // test 2: raw readback is not escaped
        String rawHeader = message.getFirstHeader("Subject");
        assertEquals("plain subject not encoded", -1, rawHeader.indexOf("=?"));

        // test 3: long subject (shouldn't fold)
        message.setSubject(LONG_PLAIN_64);
        rawHeader = message.getFirstHeader("Subject");
        String[] split = rawHeader.split("\r\n");
        assertEquals("64 shouldn't fold", 1, split.length);

        // test 4: very long subject (should fold)
        message.setSubject(LONG_PLAIN_256);
        rawHeader = message.getFirstHeader("Subject");
        split = rawHeader.split("\r\n");
        assertTrue("long subject should fold", split.length > 1);
        for (String s : split) {
            assertTrue("split lines max length 78", s.length() <= 76);  // 76+\r\n = 78
            String trimmed = s.trim();
            assertFalse("split lines are not encoded", trimmed.startsWith("=?"));
        }
    }
