    public static String toUTF8String(byte[] bytes) {
        if (bytes == null) {
            return null;
        }

        try {
            return new String(bytes, UTF8);
        } catch (UnsupportedEncodingException e) {
            throw new CmisRuntimeException("Unsupported encoding 'UTF-8'!", e);
        }
    }
