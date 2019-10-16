    public static byte[] toUTF8Bytes(String s) {
        if (s == null) {
            return null;
        }

        try {
            return s.getBytes(UTF8);
        } catch (UnsupportedEncodingException e) {
            throw new CmisRuntimeException("Unsupported encoding 'UTF-8'!", e);
        }
    }
