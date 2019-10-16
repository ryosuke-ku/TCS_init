    public void testSmsEncodeDecodeNativeSubmitPduWithSc() {
        BluetoothMapbMessageSms msg = new BluetoothMapbMessageSms();
        String encoded =
                 "BEGIN:BMSG\r\n" +
                    "VERSION:1.0\r\n" +
                    "STATUS:UNREAD\r\n" +
                    "TYPE:SMS_GSM\r\n" +
                    "FOLDER:telecom/msg/outbox\r\n" +
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
                            "LENGTH:58 \r\n" +
                            "BEGIN:MSG\r\n" + /*Length 11 */
                                "018001000B912184254590F500000346F61B\r\n" + /* Length 38 */
                            "END:MSG\r\n" + /* Length 9 */
                        "END:BBODY\r\n" +
                    "END:BENV\r\n" +
                 "END:BMSG\r\n";
        try {
            String expected = "Flo";
            InputStream is = new ByteArrayInputStream(encoded.getBytes("UTF-8"));
            BluetoothMapbMessage newMsg = BluetoothMapbMessage.parse(is, BluetoothMapAppParams.CHARSET_NATIVE);
            String decoded = ((BluetoothMapbMessageSms) newMsg).getSmsBody();
            if(D) Log.d(TAG, "\nEncoded: \n" + encoded);
            if(D) Log.d(TAG, "\nDecoded: \n" + decoded);
            assertTrue("Decoded string (" + decoded + ") did not match expected (" + expected + ")", expected.equals(decoded));
        } catch (UnsupportedEncodingException e) {
            Log.d(TAG, "Encoding failed.",e);
            assertTrue("Encoding failed.", false);
        }
    }
