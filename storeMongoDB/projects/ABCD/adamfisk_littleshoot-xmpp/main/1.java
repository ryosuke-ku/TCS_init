    public static String toString(final Packet msg) {
        final XMPPError error = msg.getError();
        final StringBuilder sb = new StringBuilder();
        sb.append("\nMESSAGE: ");
        sb.append("\nBODY: ");
        if (msg instanceof Message) {
            sb.append(((Message)msg).getBody());
        }
        sb.append("\nFROM: ");
        sb.append(msg.getFrom());
        sb.append("\nTO: ");
        sb.append(msg.getTo());
        sb.append("\nSUBJECT: ");
        if (msg instanceof Message) {
            sb.append(((Message)msg).getSubject());
        }
        sb.append("\nPACKET ID: ");
        sb.append(msg.getPacketID());

        sb.append("\nERROR: ");
        if (error != null) {
            sb.append(error);
            sb.append("\nCODE: ");
            sb.append(error.getCode());
            sb.append("\nMESSAGE: ");
            sb.append(error.getMessage());
            sb.append("\nCONDITION: ");
            sb.append(error.getCondition());
            sb.append("\nEXTENSIONS: ");
            sb.append(error.getExtensions());
            sb.append("\nTYPE: ");
            sb.append(error.getType());
        }
        sb.append("\nEXTENSIONS: ");
        sb.append(msg.getExtensions());
        sb.append("\nTYPE: ");
        if (msg instanceof Message) {
            sb.append(((Message)msg).getType());
        }
        sb.append("\nPROPERTY NAMES: ");
        sb.append(msg.getPropertyNames());
        return sb.toString();
    }
