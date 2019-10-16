    public void testSmsEncodeNativeDeliverPdu() {
        BluetoothMapbMessageSms msg = new BluetoothMapbMessageSms();
        SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
        Date date = new Date(System.currentTimeMillis());
        String timeStr = format.format(date); // Format to YYMMDDTHHMMSS UTC time
        ByteArrayOutputStream scTime = new ByteArrayOutputStream(7);
        StringBuilder scTimeSb = new StringBuilder();
        byte[] timeChars;
        try {
            timeChars = timeStr.getBytes("US-ASCII");
        } catch (UnsupportedEncodingException e1) {
            assertTrue("Failed to extract bytes from string using US-ASCII", true);
            return;
        }

        for(int i = 0, n = timeStr.length(); i < n; i+=2) {
            scTime.write((timeChars[i+1]-0x30) << 4 | (timeChars[i]-0x30)); // Offset from ascii char to decimal value
        }

        Calendar cal = Calendar.getInstance();
        int offset = (cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET)) / (15 * 60 * 1000); /* offset in quarters of an hour */
        String offsetString;
        if(offset < 0) {
            offsetString = String.format("%1$02d", -(offset));
            char[] offsetChars = offsetString.toCharArray();
            scTime.write((offsetChars[1]-0x30) << 4 | 0x40 | (offsetChars[0]-0x30));
        }
        else {
            offsetString = String.format("%1$02d", offset);
            char[] offsetChars = offsetString.toCharArray();
            scTime.write((offsetChars[1]-0x30) << 4 | (offsetChars[0]-0x30));
        }
        byte[] scTimeData = scTime.toByteArray();
        for(int i = 0; i < scTimeData.length; i++) {
            scTimeSb.append(Integer.toString((scTimeData[i] >> 4) & 0x0f,16)); // MS-nibble first
            scTimeSb.append(Integer.toString( scTimeData[i]       & 0x0f,16));
        }
        if(D) Log.v(TAG, "Generated time string: " + scTimeSb.toString());
        String expected =
                 "BEGIN:BMSG\r\n" +
                    "VERSION:1.0\r\n" +
                    "STATUS:UNREAD\r\n" +
                    "TYPE:SMS_GSM\r\n" +
                    "FOLDER:telecom/msg/inbox\r\n" +
                    "BEGIN:VCARD\r\n" +
                        "VERSION:3.0\r\n" +
                        "FN:Casper Bonde\r\n" +
                        "N:Bonde,Casper\r\n" +
                        "TEL:00498912345678\r\n" +
                        "TEL:+4587654321\r\n" +
                        "EMAIL:casper@email.add\r\n" +
                        "EMAIL:bonde@email.add\r\n" +
                    "END:VCARD\r\n" +
                    "BEGIN:BENV\r\n" +
                        "BEGIN:VCARD\r\n" +
                            "VERSION:3.0\r\n" +
                            "FN:Jens Hansen\r\n" +
                            "N:\r\n" +
                            "TEL:00498912345678\r\n" +
                            "TEL:+4587654321\r\n" +
                            "EMAIL:casper@email.add\r\n" +
                            "EMAIL:bonde@email.add\r\n" +
                        "END:VCARD\r\n" +
                        "BEGIN:BBODY\r\n" +
                            "ENCODING:G-7BIT\r\n" +
                            "LENGTH:94\r\n" +
                            "BEGIN:MSG\r\n" +
                                "00040E81009498214365870000" + scTimeSb.toString() +
                                "11CC32FD34079DDF20737A8E4EBBCF21\r\n" +
                            "END:MSG\r\n" +
                        "END:BBODY\r\n" +
                    "END:BENV\r\n" +
                 "END:BMSG\r\n";

        String encoded;
        String[] phone = {"00498912345678", "+4587654321"};
        String[] email = {"casper@email.add", "bonde@email.add"};
        msg.addOriginator("Bonde,Casper", "Casper Bonde", phone, email);
        msg.addRecipient("", "Jens Hansen", phone, email);
        msg.setFolder("inbox");
        /* TODO: extract current time, and build the expected string */
        msg.setSmsBodyPdus(BluetoothMapSmsPdu.getDeliverPdus("Let's go fishing!", "00498912345678", date.getTime()));
        msg.setStatus(false);
        msg.setType(TYPE.SMS_GSM);
        try {
            byte[] encodedBytes = msg.encode();
//            InputStream is = new ByteArrayInputStream(encodedBytes);
            encoded = new String(encodedBytes);
//            BluetoothMapbMessage newMsg = BluetoothMapbMessage.parse(is, BluetoothMapAppParams.CHARSET_NATIVE);
//            String decoded = ((BluetoothMapbMessageSms) newMsg).getSmsBody();
            if(D) Log.d(TAG, "\nExpected: \n" + expected);
            if(D) Log.d(TAG, "\nEncoded: \n" + encoded);
//            if(D) Log.d(TAG, "\nDecoded: \n" + decoded);
            assertTrue(expected.equalsIgnoreCase(encoded));
        } catch (UnsupportedEncodingException e) {
            Log.d(TAG, "Encoding failed.",e);
            assertTrue("Encoding failed.", true);
        }
    }
