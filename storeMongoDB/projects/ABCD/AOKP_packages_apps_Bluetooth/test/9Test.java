    public void testMmsEncodeText() {
        BluetoothMapbMessageMmsEmail msg = new BluetoothMapbMessageMmsEmail();
        String str1 =
                 "BEGIN:BMSG\r\n" +
                    "VERSION:1.0\r\n" +
                    "STATUS:UNREAD\r\n" +
                    "TYPE:MMS\r\n" +
                    "FOLDER:telecom/msg/inbox\r\n" +
                    "BEGIN:VCARD\r\n" +
                        "VERSION:3.0\r\n" +
                        "FN:Casper Bonde\r\n" +
                        "N:Bonde,Casper\r\n" +
                        "TEL:+4512345678\r\n" +
                        "TEL:+4587654321\r\n" +
                        "EMAIL:casper@email.add\r\n" +
                        "EMAIL:bonde@email.add\r\n" +
                    "END:VCARD\r\n" +
                    "BEGIN:BENV\r\n" +
                        "BEGIN:VCARD\r\n" +
                            "VERSION:3.0\r\n" +
