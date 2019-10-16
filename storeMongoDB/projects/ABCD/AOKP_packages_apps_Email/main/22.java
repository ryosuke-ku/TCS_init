    public void writeTo(OutputStream out) throws IOException, MessagingException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out), 1024);
        // Force creation of local message-id
        getMessageId();
        getMimeHeaders().writeTo(out);
        // mExtendedHeader will not be write out to external output stream,
        // because it is intended to internal use.
        writer.write("\r\n");
        writer.flush();
        if (mBody != null) {
            mBody.writeTo(out);
        }
    }
