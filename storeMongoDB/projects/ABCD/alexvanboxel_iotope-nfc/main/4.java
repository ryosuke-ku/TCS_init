    public static byte[] bin2hex(String s) {
        ByteArrayDataOutput dataOutput = ByteStreams.newDataOutput();
        int len = s.length();
        boolean commentMode = false;
        for (int i = 0; i < len; i += 1) {
            char charAt = s.charAt(i);
            if (commentMode) {
                if (charAt == '\n') {
                    commentMode = false;
                }
            } else {
                if (Character.isLetterOrDigit(charAt)) {
                    dataOutput.writeByte((byte) ((Character.digit(charAt, 16) << 4) + Character.digit(s.charAt(i + 1), 16)));
                    i += 1;
                } else if (charAt == '/' && i + 1 < len && s.charAt(i + 1) == '/') {
                    commentMode = true;
                    i += 1;
                }
            }
        }
        return dataOutput.toByteArray();
    }
