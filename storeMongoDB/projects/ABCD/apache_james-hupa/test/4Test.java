    @Test public void getRecipients () throws Exception  {
        String encodedEmail = "=?ISO-8859-1?Q?Manolo_Pe=F1a?= <hello@hupa.org>";
        String decodedEmail = MessageUtils.decodeText(encodedEmail);
        assertFalse(encodedEmail.equals(decodedEmail));

        Address[] addr = MessageUtils.getRecipients(encodedEmail, decodedEmail);
        assertEquals(addr[0].toString(), addr[1].toString());
    }
