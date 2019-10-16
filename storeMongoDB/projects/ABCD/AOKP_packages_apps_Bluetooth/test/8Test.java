    public void testFolderLengthTruncation() {
        String folder = "";
        int levelCount = 0;
        while(folder.length()<640)
            folder += "/folder" + levelCount++;

        String expected = folder.substring(folder.length()-512, folder.length());

        BluetoothMapbMessageSms msg = new BluetoothMapbMessageSms();
        msg.setFolder(folder);
        msg.setStatus(false);
        msg.setType(TYPE.SMS_GSM);

        try {
            byte[] encoded = msg.encode();
            InputStream is = new ByteArrayInputStream(encoded);
            if(D) Log.d(TAG, new String(encoded));
            BluetoothMapbMessage newMsg = BluetoothMapbMessage.parse(is, BluetoothMapAppParams.CHARSET_UTF8);
            assertTrue("Wrong length expected 512, got " + expected.length(), expected.length() == 512);
            Log.d(TAG, "expected:           " + expected);
            Log.d(TAG, "newMsg.getFolder(): " + newMsg.getFolder());
            assertTrue("Folder string did not match", expected.equals(newMsg.getFolder()));

        } catch (UnsupportedEncodingException e) {
            Log.d(TAG, "Encoding failed.",e);
            assertTrue("Encoding failed", false);
        }
    }
