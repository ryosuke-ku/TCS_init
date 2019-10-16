    public void testEncodingAddressField() throws MessagingException {
        Address noName1 = new Address("noname1@dom1.com");
        Address noName2 = new Address("<noname2@dom2.com>", "");
        Address simpleName = new Address("address3@dom3.org", "simple long and long long name");
        Address dquoteName = new Address("address4@dom4.org", "name,4,long long name");
        Address quotedName = new Address("bigG@dom5.net", "big \"G\"");
        Address utf16Name = new Address("<address6@co.jp>", "\"\u65E5\u672C\u8A9E\"");
        Address utf32Name = new Address("<address8@ne.jp>", "\uD834\uDF01\uD834\uDF46");

        MimeMessage message = new MimeMessage();

        message.setFrom(noName1);
        message.setRecipient(RecipientType.TO, noName2);
        message.setRecipients(RecipientType.CC, new Address[] { simpleName, dquoteName });
        message.setReplyTo(new Address[] { quotedName, utf16Name, utf32Name });

        String[] from = message.getHeader("From");
        String[] to = message.getHeader("To");
        String[] cc = message.getHeader("Cc");
        String[] replyTo = message.getHeader("Reply-to");

        assertEquals("from address count", 1, from.length);
        assertEquals("no name 1", "noname1@dom1.com", from[0]);

        assertEquals("to address count", 1, to.length);
        assertEquals("no name 2", "noname2@dom2.com", to[0]);

        // folded.
        assertEquals("cc address count", 1, cc.length);
        assertEquals("simple name & double quoted name",
                "simple long and long long name <address3@dom3.org>, \"name,4,long long\r\n"
                + " name\" <address4@dom4.org>",
                cc[0]);

        // folded and encoded.
        assertEquals("reply-to address count", 1, replyTo.length);
        assertEquals("quoted name & encoded name",
                "\"big \\\"G\\\"\" <bigG@dom5.net>, =?UTF-8?B?5pel5pys6Kqe?=\r\n"
                + " <address6@co.jp>, =?UTF-8?B?8J2MgfCdjYY=?= <address8@ne.jp>",
                replyTo[0]);
    }
