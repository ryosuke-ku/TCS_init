    public static BluetoothMapbMessage parse(InputStream bMsgStream, int appParamCharset) throws IllegalArgumentException{
        BMsgReader reader;
        String line = "";
        BluetoothMapbMessage newBMsg = null;
        boolean status = false;
        boolean statusFound = false;
        TYPE type = null;
        String folder = null;

        /* This section is used for debug. It will write the incoming message to a file on the SD-card,
         * hence should only be used for test/debug.
         * If an error occurs, it will result in a OBEX_HTTP_PRECON_FAILED to be send to the client,
         * even though the message might be formatted correctly, hence only enable this code for test. */
        if(V) {
            /* Read the entire stream into a file on the SD card*/
            File sdCard = Environment.getExternalStorageDirectory();
            File dir = new File (sdCard.getAbsolutePath() + "/bluetooth/log/");
            dir.mkdirs();
            File file = new File(dir, "receivedBMessage.txt");
            FileOutputStream outStream = null;
            boolean failed = false;
            int writtenLen = 0;

            try {
                outStream = new FileOutputStream(file, false); /* overwrite if it does already exist */

                byte[] buffer = new byte[4*1024];
                int len = 0;
                while ((len = bMsgStream.read(buffer)) > 0) {
                    outStream.write(buffer, 0, len);
                    writtenLen += len;
                }
            } catch (FileNotFoundException e) {
                Log.e(TAG,"Unable to create output stream",e);
            } catch (IOException e) {
                Log.e(TAG,"Failed to copy the received message",e);
                if(writtenLen != 0)
                    failed = true; /* We failed to write the complete file, hence the received message is lost... */
            } finally {
                if(outStream != null)
                    try {
                        outStream.close();
                    } catch (IOException e) {
                    }
            }

            /* Return if we corrupted the incoming bMessage. */
            if(failed) {
                throw new IllegalArgumentException(); /* terminate this function with an error. */
            }

            if (outStream == null) {
                /* We failed to create the the log-file, just continue using the original bMsgStream. */
            } else {
                /* overwrite the bMsgStream using the file written to the SD-Card */
                try {
                    bMsgStream.close();
                } catch (IOException e) {
                    /* Ignore if we cannot close the stream. */
                }
                /* Open the file and overwrite bMsgStream to read from the file */
                try {
                    bMsgStream = new FileInputStream(file);
                } catch (FileNotFoundException e) {
                    Log.e(TAG,"Failed to open the bMessage file", e);
                    throw new IllegalArgumentException(); /* terminate this function with an error. */
                }
            }
            Log.i(TAG, "The incoming bMessage have been dumped to " + file.getAbsolutePath());
        } /* End of if(V) log-section */

        reader = new BMsgReader(bMsgStream);
        reader.expect("BEGIN:BMSG");
        reader.expect("VERSION","1.0");

        line = reader.getLineEnforce();
        // Parse the properties - which end with either a VCARD or a BENV
        while(!line.contains("BEGIN:VCARD") && !line.contains("BEGIN:BENV")) {
            if(line.contains("STATUS")){
                String arg[] = line.split(":");
                if (arg != null && arg.length == 2) {
                    if (arg[1].trim().equals("READ")) {
                        status = true;
                    } else if (arg[1].trim().equals("UNREAD")) {
                        status =false;
                    } else {
                        throw new IllegalArgumentException("Wrong value in 'STATUS': " + arg[1]);
                    }
                } else {
                    throw new IllegalArgumentException("Missing value for 'STATUS': " + line);
                }
            }
            if(line.contains("TYPE")) {
                String arg[] = line.split(":");
                if (arg != null && arg.length == 2) {
                    String value = arg[1].trim();
                    type = TYPE.valueOf(value); // Will throw IllegalArgumentException if value is wrong
                    if(appParamCharset == BluetoothMapAppParams.CHARSET_NATIVE
                            && type != TYPE.SMS_CDMA && type != TYPE.SMS_GSM) {
                        throw new IllegalArgumentException("Native appParamsCharset only supported for SMS");
                    }
                    switch(type) {
                    case SMS_CDMA:
                    case SMS_GSM:
                        newBMsg = new BluetoothMapbMessageSms();
                        break;
                    case MMS:
                    case EMAIL:
                        newBMsg = new BluetoothMapbMessageMmsEmail();
                        break;
                    default:
                        break;
                    }
                } else {
                    throw new IllegalArgumentException("Missing value for 'TYPE':" + line);
                }
            }
            if(line.contains("FOLDER")) {
                String[] arg = line.split(":");
                if (arg != null && arg.length == 2) {
                    folder = arg[1].trim();
                }
                // This can be empty for push message - hence ignore if there is no value
            }
            line = reader.getLineEnforce();
        }
        if(newBMsg == null)
            throw new IllegalArgumentException("Missing bMessage TYPE: - unable to parse body-content");
        newBMsg.setType(type);
        newBMsg.appParamCharset = appParamCharset;
        if(folder != null)
            newBMsg.setCompleteFolder(folder);
        if(statusFound)
            newBMsg.setStatus(status);

        // Now check for originator VCARDs
        while(line.contains("BEGIN:VCARD")){
            if(D) Log.d(TAG,"Decoding vCard");
            newBMsg.addOriginator(vCard.parseVcard(reader,0));
            line = reader.getLineEnforce();
        }
        if(line.contains("BEGIN:BENV")) {
            newBMsg.parseEnvelope(reader, 0);
        } else
            throw new IllegalArgumentException("Bmessage has no BEGIN:BENV - line:" + line);

        /* TODO: Do we need to validate the END:* tags? They are only needed if someone puts additional info
         *        below the END:MSG - in which case we don't handle it.
         */

        try {
            bMsgStream.close();
        } catch (IOException e) {
            /* Ignore if we cannot close the stream. */
        }

        return newBMsg;
    }
